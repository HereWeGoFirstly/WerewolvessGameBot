package model;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class GameRole {
    private String name;
    private RoleAction action;
}
