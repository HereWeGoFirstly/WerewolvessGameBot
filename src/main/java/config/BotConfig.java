package config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.bot.AbilityBot;

@Component
public class BotConfig extends AbilityBot {
    @Value("${bot.name}")
    private String botName;
    @Value("${bot.token}")
    private String token;

    public BotConfig() {
        super("", "");


    }

    @Override
    public long creatorId() {
        return 0;
    }
}
