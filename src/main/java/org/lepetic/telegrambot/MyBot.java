package org.lepetic.telegrambot;

import org.lepetic.telegrambot.abilities.EventSubscription;
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

    public Ability sayFuisteAgregadoALaLista() {
        return buildAbility("add", "adds you to the group's current event", ALL, PUBLIC,
                ctx -> {
                    EventSubscription eventSubscription = new EventSubscription();
                    User user = ctx.user();
                    String nickname = Optional.ofNullable(user.getUserName()).orElse(user.getFirstName() + " " + Optional.ofNullable(user.getLastName()).orElse(""));
                    eventSubscription.addToOrganisedEvent(ctx.chatId(), nickname);
                    silent.send(nickname + " fuiste agregado a la lista", ctx.chatId());
                });
    }

}
