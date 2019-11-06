package salvo.salvo;

//import com.sun.xml.internal.bind.v2.model.core.ID;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;
//import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
public class SalvoController<SalvoRepository> {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private GamePlayerRepository gamePlayerRepository;
    @Autowired
    private ScoreRepository scoreRepository;
    @Autowired
    private PlayerRepository playerRepository;



    @RequestMapping(path = "/api/players", method = RequestMethod.POST)
    public ResponseEntity<Object> register(
            @RequestParam String userName, @RequestParam String password) {

        if (userName.isEmpty() || password.isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

        if (playerRepository.findByUserName(userName) !=  null) {
            return new ResponseEntity<>("Name already in use", HttpStatus.FORBIDDEN);
        }

        playerRepository.save(new Player(userName, passwordEncoder.encode(password)));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping("/api/games")
    public List<Map<String,Object>> getAllGames() {
        return gameRepository.findAll()
                .stream()
                .map(game -> gameDTO(game))
                .collect(Collectors.toList());
    }


    public List<Map<String,Object>> getAllScores(){
        return scoreRepository.findAll()
                .stream()
                .map(score -> scoreDTO(score))
                .collect(Collectors.toList());
    }



    @RequestMapping("/api/games_view/{gamePlayerId}")
    private Map<String,Object> gameView (@PathVariable long gamePlayerId,
                                         Authentication authentication) {
        Map<String, Object> map = new LinkedHashMap<>();
        Player currentUser = playerRepository.findByUserName(authentication.getName()).get(0);
        GamePlayer user = gamePlayerRepository.findById(gamePlayerId).orElse(null);
        GamePlayer enemy = getEnemy(user);
        if (user != null) {
            if (currentUser.getId() == user.getPlayer().getId()) {
                map.put("currentPlayer", playerDTO(currentUser));
                map.put("game", gameDTO(user.getGame()));
                map.put("userShips", user.getShips().stream().map(ship -> shipDTO(ship)).collect(Collectors.toList()));
                if (enemy != null) {
                    map.put("userSalvos", user.getSalvo().stream().map(salvo -> salvoDTO(salvo)).collect(Collectors.toList()));
                    map.put("enemySalvos", enemy.getSalvo().stream().map(salvo -> salvoDTO(salvo)).collect(Collectors.toList()));
                }
            } else {
                map.put("error","Error: You can not access to this game");
            }

        } else {
            map.put("error","Error: User Not Found");
        }
        return map;
    }

    @RequestMapping("/api/scores")

    public List<Object> getAllPlayersScores(){
        Map<String, Object> map = new LinkedHashMap<>();


      return playerRepository.findAll()
              .stream()
              .map(player -> calculateScores(player))
              .collect(Collectors.toList());

    }

    public Map<String,Object> calculateScores (Player player) {
        Map<String,Object> map  = new LinkedHashMap<>();
        Set<Score> scores = player.getScores();

        int wins = 0;
        int loses = 0;
        int ties = 0;
        double totalPoints = 0;

        for (Score score : scores) {
            if( score.getScore() == 1){
                wins++;
            }
            if( score.getScore() == 0){
                loses++;
            }
            if( score.getScore() == 0.5){
                ties++;
            }
            totalPoints += score.getScore();
        }
        map.put("name", player.getUserName());
        map.put("win", wins);
        map.put("loss", loses);
        map.put("tie", ties);
        map.put("score", totalPoints);

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


    private Map<String, Object> scoreDTO(Score score){
        Map<String, Object> map =new LinkedHashMap<>();
        map.put("score", score.getScore());
        map.put ("game", score.getGame());
        map.put ("player", score.getPlayer());
        return map;
    }


}

