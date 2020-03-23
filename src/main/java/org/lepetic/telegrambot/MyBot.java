package org.lepetic.telegrambot;

import org.lepetic.telegrambot.abilities.EventModification;
import org.lepetic.telegrambot.abilities.EventSubscription;
import org.lepetic.telegrambot.entities.OrganisedEvent;
import org.lepetic.telegrambot.exceptions.arguments.InvalidArgumentCountException;
import org.lepetic.telegrambot.exceptions.dateTime.ScheduleInThePastException;
import org.lepetic.telegrambot.exceptions.events.EventAlreadyExistingException;
import org.lepetic.telegrambot.exceptions.events.MemberAlreadySubscribedException;
import org.lepetic.telegrambot.exceptions.events.MemberIsNotSubscribedException;
import org.lepetic.telegrambot.exceptions.events.NoEventRegisteredException;
import org.lepetic.telegrambot.exceptions.format.InvalidFormatException;
import org.lepetic.telegrambot.utils.ArgumentsValidator;
import org.lepetic.telegrambot.utils.MessageBuilder;
import org.lepetic.telegrambot.utils.TelegramUtils;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.abilitybots.api.objects.Ability.AbilityBuilder;
import org.telegram.abilitybots.api.objects.Locality;
import org.telegram.abilitybots.api.objects.MessageContext;
import org.telegram.abilitybots.api.objects.Privacy;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Arrays;
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

    private AbilityBuilder basicAbility(String abilityName, String abilityDescription, Locality locality,
                                        Privacy privacy){
        return Ability
                .builder()
                .name(abilityName)
                .info(abilityDescription)
                .locality(locality)
                .privacy(privacy);
    }

    private Ability buildAbility(String abilityName, String abilityDescription, Locality locality,
                                 Privacy privacy, Consumer<MessageContext> consumer) {
        return basicAbility(abilityName, abilityDescription, locality, privacy).action(consumer).build();
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
                        String message = MessageBuilder.buildEventMessage(organisedEvent);
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
                        String message = MessageBuilder.buildEventMessage(organisedEvent);
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
                        ArgumentsValidator.validateNewEvent(ctx.arguments());
                        EventSubscription eventSubscription = new EventSubscription();
                        eventSubscription.createOrganisedEvent(ctx.chatId(), ctx.firstArg(), ctx.secondArg(),
                                Arrays.copyOfRange(ctx.arguments(), 2, ctx.arguments().length));
                        silent.send("El evento ha sido creado", ctx.chatId());
                    } catch (InvalidArgumentCountException e) {
                        silent.send("Los argumentos minimos son 3: /event <dia> <hora> <nombre>",
                                ctx.chatId());
                    } catch (InvalidFormatException e) {
                        silent.send("Recuerde respetar los formatos dd/MM/yyyy y hh:mm.", ctx.chatId());
                    } catch (ScheduleInThePastException e) {
                        silent.send("Los eventos no se pueden crear en el pasado", ctx.chatId());
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

    public Ability changeDate() {
        return buildAbility("eventDate", "changes the date of the event", GROUP, ADMIN,
                ctx -> {
                    ArgumentsValidator.validateNewEvent(ctx.arguments());
                    EventModification eventModification = new EventModification();
                    eventModification.changeDate(ctx.chatId(), ctx.firstArg());
                });
    }

}
