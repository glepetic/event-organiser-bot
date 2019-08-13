package org.lepetic.telegrambot;

import org.lepetic.telegrambot.abilities.EventSubscription;
import org.lepetic.telegrambot.entities.OrganisedEvent;
import org.lepetic.telegrambot.exceptions.EventAlreadyExistingException;
import org.lepetic.telegrambot.exceptions.MemberAlreadySubscribedException;
import org.lepetic.telegrambot.exceptions.MemberIsNotSubscribedException;
import org.lepetic.telegrambot.exceptions.NoEventRegisteredException;
import org.lepetic.telegrambot.utils.MessageBuilder;
import org.lepetic.telegrambot.utils.TelegramUtils;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.abilitybots.api.objects.Locality;
import org.telegram.abilitybots.api.objects.MessageContext;
import org.telegram.abilitybots.api.objects.Privacy;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.function.Consumer;

import static org.telegram.abilitybots.api.objects.Locality.GROUP;
import static org.telegram.abilitybots.api.objects.Privacy.ADMIN;
import static org.telegram.abilitybots.api.objects.Privacy.PUBLIC;

public class MyBot extends AbilityBot {

    //my telegram id
    private static final int CREATOR_ID = 923044300;

    protected MyBot(String botToken, String botUsername) {
        super(botToken, botUsername);
    }

    @Override
    public int creatorId() {
        return CREATOR_ID;
    }

    private Ability buildAbility(String abilityName, String abilityDescription, Locality locality, Privacy privacy,
                                 Consumer<MessageContext> consumer) {
        return Ability
                .builder()
                .name(abilityName)
                .info(abilityDescription)
                .locality(locality)
                .privacy(privacy)
                .action(consumer)
                .build();
    }

    public Ability addUserToEvent() {
        return buildAbility("join", "adds you to the group's current event", GROUP, PUBLIC,
                ctx -> {
                    EventSubscription eventSubscription = new EventSubscription();
                    User user = ctx.user();
                    String nickname = TelegramUtils.getUserNickName(user);
                    OrganisedEvent organisedEvent;
                    try {
                        organisedEvent = eventSubscription.addToOrganisedEvent(ctx.chatId(), nickname, user.getId());
                        String message = MessageBuilder.buildEventMessage(organisedEvent.getEventName(), organisedEvent.participants());
                        silent.send(message, ctx.chatId());
                    } catch (NoEventRegisteredException e) {
                        silent.send("No hay evento actual para este grupo. Si sos admin, crealo con /event",
                                ctx.chatId());
                    } catch (MemberAlreadySubscribedException e) {
                        silent.send("Ya estas registrado para este evento", ctx.chatId());
                    }
                });
    }

    public Ability removeUserFromEvent() {
        return buildAbility("leave", "removes you from the group's current event", GROUP, PUBLIC,
                ctx -> {
                    EventSubscription eventSubscription = new EventSubscription();
                    User user = ctx.user();
                    String nickname = TelegramUtils.getUserNickName(user);
                    OrganisedEvent organisedEvent;
                    try {
                        organisedEvent = eventSubscription.removeFromOrganisedEvent(ctx.chatId(), nickname, user.getId());
                        String message = MessageBuilder.buildEventMessage(organisedEvent.getEventName(), organisedEvent.participants());
                        silent.send(message, ctx.chatId());
                    } catch (NoEventRegisteredException e) {
                        silent.send("No hay evento actual para este grupo. Si sos admin, crealo con /event",
                                ctx.chatId());
                    } catch (MemberIsNotSubscribedException e) {
                        silent.send("No estas registrado para este evento", ctx.chatId());
                    }
                }
        );
    }

    public Ability createNewEvent() {
        return buildAbility("event", "creates a new event", GROUP, ADMIN,
                ctx -> {
                    try {
                        EventSubscription eventSubscription = new EventSubscription();
                        eventSubscription.createOrganisedEvent(ctx.chatId(), ctx.firstArg());
                        silent.send("El evento ha sido creado", ctx.chatId());
                    } catch (IllegalStateException e) {
                        silent.send("El comando /event requiere el nombre del evento que se desea crear", ctx.chatId());
                    } catch (EventAlreadyExistingException e) {
                        silent.send("Ya existe un evento para este grupo. Puede modificarlo o eliminarlo si desea crear uno nuevo",
                                ctx.chatId());
                    }
                });
    }

    public Ability deleteEvent() {
        return buildAbility("delete", "deletes an existing event", GROUP, ADMIN,
                ctx -> {
                    try {
                        EventSubscription eventSubscription = new EventSubscription();
                        eventSubscription.deleteOrganisedEvent(ctx.chatId());
                        silent.send("El evento ha sido eliminado", ctx.chatId());
                    } catch (NoEventRegisteredException e) {
                        silent.send("No existe un evento en este grupo.",
                                ctx.chatId());
                    }
                });
    }

}
