package salvo.salvo;

//import com.sun.xml.internal.bind.v2.model.core.ID;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;


@RestController
public class SalvoController<SalvoRepository> {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private GamePlayerRepository gamePlayerRepository;
//    @Autowired
//    private SalvoRepository salvoRepository;

    @RequestMapping("/api/games")
    public List<Map<String,Object>> getAllGames() {
        return gameRepository.findAll()
                .stream()
                .map(game -> gameDTO(game))
                .collect(Collectors.toList());
    }

    @RequestMapping("/api/games_view/{gamePlayerId}")
    private Map<String,Object> gameView (@PathVariable long gamePlayerId) {
        Map<String, Object> map = new LinkedHashMap<>();

        GamePlayer user = gamePlayerRepository.findById(gamePlayerId).orElse(null);
        GamePlayer enemy = getEnemy(user);
        if (user != null) {
            map.put("currentPlayer", playerDTO(user.getPlayer()));
            map.put("game", gameDTO(user.getGame()));
            map.put("userShips", user.getShips().stream().map(ship -> shipDTO(ship)).collect(Collectors.toList()));
            if (enemy != null) {
                map.put("userSalvos", user.getSalvo().stream().map(salvo -> salvoDTO(salvo)).collect(Collectors.toList()));
                map.put("enemySalvos", enemy.getSalvo().stream().map(salvo -> salvoDTO(salvo)).collect(Collectors.toList()));
            }
        } else {
            map.put("error","Error: User Not Found");
        }

        return map;
    }

    private GamePlayer getEnemy (GamePlayer gamePlayer) {
        return gamePlayer
                .getGame()
                .getGamePlayer()
                .stream()
                .filter(gp -> gp.getId() != gamePlayer.getId())
                .findFirst()
                .orElse(null);
    }



    private Map<String,Object> gameDTO (Game game) {
        Map <String, Object> map = new LinkedHashMap<String, Object>();


        map.put("id", game.getId());
        map.put("date", game.getDate());
        map.put("gamePlayers", game.getGamePlayer()
                .stream()
                .map(gamePlayer -> gamePlayerDTO(gamePlayer))
                .collect(Collectors.toList()));
        return map;
    }

    private Map<String, Object> gamePlayerDTO(GamePlayer gamePlayer){
            Map<String, Object> map = new LinkedHashMap<String, Object>();

            map.put("id", gamePlayer.getId());
            map.put("players", playerDTO(gamePlayer.getPlayer()));

        return map;
    }

    private Map<String, Object> playerDTO(Player player){
        Map<String, Object> map = new LinkedHashMap<String, Object>();

        map.put("id", player.getId());
        map.put("userName", player.getUserName());

        return map;
    }

    private Map<String, Object> shipDTO(Ship ship){
        Map<String, Object> map= new LinkedHashMap<String, Object>();
        map.put ("type", ship.getType());
        map.put("location", ship.getLocation());
        return  map;
    }

    private Map<String, Object> salvoDTO(Salvo salvo){
        Map <String, Object> map = new LinkedHashMap<String, Object>();
        map.put("turn", salvo.getTurn());
        map.put("locations",salvo.getLocation());
        return map;

    }

}

