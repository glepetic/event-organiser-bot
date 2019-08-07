package org.lepetic.telegrambot;

import org.lepetic.telegrambot.abilities.EventSubscription;
import org.lepetic.telegrambot.entities.OrganisedEvent;
import org.lepetic.telegrambot.exceptions.NoEventRegisteredException;
import org.lepetic.telegrambot.utils.MessageBuilder;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.abilitybots.api.objects.Locality;
import org.telegram.abilitybots.api.objects.MessageContext;
import org.telegram.abilitybots.api.objects.Privacy;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Optional;
import java.util.function.Consumer;

import static org.telegram.abilitybots.api.objects.Locality.ALL;
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

    public Ability sayHelloWorld() {
        return buildAbility("hello", "says hello world!", ALL, PUBLIC,
                ctx -> silent.send("Hello world!", ctx.chatId()));
    }

    public Ability addUserToEvent() {
        return buildAbility("add", "adds you to the group's current event", ALL, PUBLIC,
                ctx -> {
                    EventSubscription eventSubscription = new EventSubscription();
                    User user = ctx.user();
                    String nickname = Optional.ofNullable(user.getUserName()).orElseGet(() -> user.getFirstName() + " " + Optional.ofNullable(user.getLastName()).orElseGet(() -> ""));
                    OrganisedEvent organisedEvent;
                    try {
                        organisedEvent = eventSubscription.addToOrganisedEvent(ctx.chatId(), nickname, user.getId());
                        String message = MessageBuilder.buildParticipantsMessage(organisedEvent.getEventName(), organisedEvent.participants());
                        silent.send(message, ctx.chatId());
                    }catch(NoEventRegisteredException e){
                        silent.send("No hay evento actual para este grupo. Si sos admin, crealo con /event",
                                ctx.chatId());
                    }
                });
    }

}
