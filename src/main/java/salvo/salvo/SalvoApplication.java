package salvo.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//import sun.util.calendar.BaseCalendar;

//import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(PlayerRepository playerRepository,
									  GameRepository gameRepository,
									  GamePlayerRepository gamePlayerRepository,
									  ShipRepository shipRepository,
									  SalvoRepository salvoRepository,
									  ScoreRepository scoreRepository
	){
		return (args)->{


			Player playerOne= new Player("j.bauer@ctu.gov");
			playerRepository.save(playerOne);
			Player playerTwo= new Player("c.obrian@ctu.gov");
			playerRepository.save(playerTwo);
			Player playerThree=new Player("kim_bauer@gmail.com");
			playerRepository.save(playerThree);
			Player playerFour=new Player("t.almeida@ctu.gov");
			playerRepository.save(playerFour);


			Game gameOne = new Game();
			gameRepository.save(gameOne);
			Game gameTwo = new Game();
			gameTwo.setDate(Date.from(gameOne.getDate().toInstant().plusSeconds(3600)));
			gameRepository.save(gameTwo);
			Game gameThree= new Game();
			gameThree.setDate(Date.from(gameTwo.getDate().toInstant().plusSeconds(3600)));
			gameRepository.save(gameThree);
			Game gameFour=new Game();
			gameFour.setDate(Date.from(gameThree.getDate().toInstant().plusSeconds(3600)));
			gameRepository.save(gameFour);
			Game gameFive=new Game();
			gameFive.setDate(Date.from(gameFour.getDate().toInstant().plusSeconds(3600)));
			gameRepository.save(gameFive);
			Game gameSix=new Game();
			gameSix.setDate(Date.from(gameFive.getDate().toInstant().plusSeconds(3600)));
			gameRepository.save(gameSix);
			Game gameSeven=new Game();
			gameSeven.setDate(Date.from(gameSix.getDate().toInstant().plusSeconds(3600)));
			gameRepository.save(gameSeven);
			Game gameHeight=new Game();
			gameHeight.setDate(Date.from(gameSeven.getDate().toInstant().plusSeconds(3600)));
			gameRepository.save(gameHeight);



			GamePlayer gamePlayerOne= new GamePlayer(playerOne, gameOne);
			gamePlayerRepository.save(gamePlayerOne);
			GamePlayer gamePlayerTwo = new GamePlayer(playerTwo, gameOne);
			gamePlayerRepository.save(gamePlayerTwo);
			GamePlayer gamePlayerThree= new GamePlayer (playerOne, gameTwo);
			gamePlayerRepository.save(gamePlayerThree);
			GamePlayer gamePlayerFour= new GamePlayer (playerTwo, gameTwo);
			gamePlayerRepository.save(gamePlayerFour);
           GamePlayer gamePlayerFive= new GamePlayer(playerTwo, gameThree);
           gamePlayerRepository.save(gamePlayerFive);
           GamePlayer gamePlayerSix= new GamePlayer(playerFour, gameThree);
           gamePlayerRepository.save(gamePlayerSix);
           GamePlayer gamePlayerSeven= new GamePlayer(playerTwo, gameFour);
           gamePlayerRepository.save(gamePlayerSeven);
           GamePlayer gamePlayerHeight= new GamePlayer(playerOne, gameFour);
           gamePlayerRepository.save(gamePlayerHeight);
           GamePlayer gamePlayerNine= new GamePlayer(playerFour, gameFive);
           gamePlayerRepository.save(gamePlayerNine);
           GamePlayer gamePlayerTen= new GamePlayer(playerOne, gameFive);
           gamePlayerRepository.save(gamePlayerTen);
           GamePlayer gamePlayerEleven= new GamePlayer(playerThree, gameSix);
           gamePlayerRepository.save(gamePlayerEleven);
           GamePlayer gamePlayerTwelve= new GamePlayer(playerFour, gameSeven);
           gamePlayerRepository.save(gamePlayerTwelve);
           GamePlayer gamePlayerThirteen= new GamePlayer(playerThree, gameHeight);
           gamePlayerRepository.save(gamePlayerThirteen);
           GamePlayer gamePlayerFourteen=new GamePlayer (playerFour, gameHeight);
           gamePlayerRepository.save(gamePlayerFourteen);


			List<String> locationOne = Arrays.asList("H2","H3", "H4");
			Ship shipOne = new Ship("destroyer", gamePlayerOne, locationOne);
			shipRepository.save(shipOne);
			List<String> locationTwo = Arrays.asList("E1","F1", "G1");
			Ship shipTwo = new Ship("submarine", gamePlayerOne, locationTwo);
			shipRepository.save(shipTwo);
			List<String> locationThree = Arrays.asList("B4","B5");
			Ship shipThree = new Ship("patrolBoat", gamePlayerOne, locationThree);
			shipRepository.save(shipThree);
			List<String> locationFour = Arrays.asList("B5", "C5", "D5");
			Ship shipFour = new Ship("destroyer", gamePlayerOne, locationFour);
			shipRepository.save(shipFour);
			List<String> locationFive = Arrays.asList("F1","F2");
			Ship shipFive = new Ship("patrolBoat", gamePlayerOne, locationFive);
			shipRepository.save(shipFive);
			List<String> locationSix = Arrays.asList("B5","C5", "D5");
			Ship shipSix = new Ship("destroyer", gamePlayerTwo, locationSix);
			shipRepository.save(shipSix);
			List<String> locationSeven = Arrays.asList("C6","C7");
			Ship shipSeven = new Ship("patrolBoat", gamePlayerTwo, locationSeven);
			shipRepository.save(shipSeven);
			List<String> locationHeight = Arrays.asList("A2","A3", "A4");
			Ship shipHeight = new Ship("submarine", gamePlayerTwo, locationHeight);
			shipRepository.save(shipHeight);
			List<String> locationNine = Arrays.asList("G6","H6");
			Ship shipNine = new Ship("patrolBoat", gamePlayerTwo, locationNine);
			shipRepository.save(shipNine);
			List<String> locationTen = Arrays.asList("B5","C5", "D5");
			Ship shipTen = new Ship("destroyer", gamePlayerThree, locationTen);
			shipRepository.save(shipTen);
			List<String> locationEleven = Arrays.asList("C6","C7");
			Ship shipEleven = new Ship("patrolBoat", gamePlayerThree, locationEleven);
			shipRepository.save(shipEleven);
			List<String> locationTwelve = Arrays.asList("A2","A3", "A4");
			Ship shipTwelve = new Ship("submarine", gamePlayerThree, locationTwelve);
			shipRepository.save(shipTwelve);
			List<String> locationThirteen = Arrays.asList("G6","H6");
			Ship shipThirteen = new Ship("patrolBoat", gamePlayerThree, locationThirteen);
			shipRepository.save(shipThirteen);
			List<String> locationFourteen = Arrays.asList("B5","C5", "D5");
			Ship shipFourteen = new Ship("destroyer", gamePlayerFour, locationFourteen);
			shipRepository.save(shipFourteen);
			List<String> locationFifteen = Arrays.asList("C6","C7");
			Ship shipFifteen = new Ship("patrolBoat", gamePlayerFour, locationFifteen);
			shipRepository.save(shipFifteen);
			List<String> locationSixteen = Arrays.asList("A2","A3", "A4");
			Ship shipSixteen = new Ship("submarine", gamePlayerFour, locationSixteen);
			shipRepository.save(shipSixteen);
			List<String> locationSeventeen = Arrays.asList("G6","H6");
			Ship shipSeventeen = new Ship("patrolBoat", gamePlayerFour, locationSeventeen);
			shipRepository.save(shipSeventeen);
			List<String> locationEighteen = Arrays.asList("B5","C5", "D5");
			Ship shipEighteen = new Ship("destroyer", gamePlayerFive, locationEighteen);
			shipRepository.save(shipEighteen);
			List<String> locationNineteen = Arrays.asList("C6","C7");
			Ship shipNineteen = new Ship("patrolBoat", gamePlayerFive, locationNineteen);
			shipRepository.save(shipNineteen);
			List<String> locationTwenty = Arrays.asList("A2","A3", "A4");
			Ship shipTwenty = new Ship("submarine", gamePlayerFive, locationTwenty);
			shipRepository.save(shipTwenty);
			List<String> locationTwentyOne = Arrays.asList("G6","H6");
			Ship shipTwentyOne = new Ship("patrolBoat", gamePlayerFive, locationTwentyOne);
			shipRepository.save(shipTwentyOne);
			List<String> locationTwentyTwo = Arrays.asList("B5","C5", "D5");
			Ship shipTwentyTwo = new Ship("destroyer", gamePlayerSix, locationTwentyTwo);
			shipRepository.save(shipTwentyTwo);
			List<String> locationTwentyThree = Arrays.asList("C6","C7");
			Ship shipTwentyThree = new Ship("patrolBoat", gamePlayerSix, locationTwentyThree);
			shipRepository.save(shipTwentyThree);
			List<String> locationTwentyFour = Arrays.asList("B5","C5", "D5");
			Ship shipTwentyFour = new Ship("destroyer", gamePlayerHeight, locationTwentyFour);
			shipRepository.save(shipTwentyFour);
			List<String> locationTwentyFive = Arrays.asList("C6","C7");
			Ship shipTwentyFive = new Ship("patrolBoat", gamePlayerHeight, locationTwentyFive);
			shipRepository.save(shipTwentyFive);
			List<String> locationTwentySix = Arrays.asList("A2","A3", "A4");
			Ship shipTwentySix = new Ship("submarine", gamePlayerHeight, locationTwentySix);
			shipRepository.save(shipTwentySix);
			List<String> locationTwentySeven = Arrays.asList("G6","H6");
			Ship shipTwentySeven = new Ship("patrolBoat", gamePlayerHeight, locationTwentySeven);
			shipRepository.save(shipTwentySeven);





			List<String> locationSalvoOne = Arrays.asList("B5", "C5", "F1");
			Salvo salvoOne= new Salvo(1,gamePlayerOne,locationSalvoOne);
			salvoRepository.save(salvoOne);
			List<String> locationSalvoTwo = Arrays.asList("B4", "B5", "B6");
			Salvo salvoTwo= new Salvo(1,gamePlayerTwo,locationSalvoTwo);
			salvoRepository.save(salvoTwo);
			List<String> locationSalvoThree = Arrays.asList("F2", "D5");
			Salvo salvoThree= new Salvo(2,gamePlayerOne,locationSalvoThree);
			salvoRepository.save(salvoThree);
			List<String> locationSalvoFour = Arrays.asList("E1", "H3", "A2");
			Salvo salvoFour= new Salvo(2,gamePlayerTwo,locationSalvoFour);
			salvoRepository.save(salvoFour);

//			List<String> locationSalvoFive = Arrays.asList("A2", "A4", "G6");
//			Salvo salvoFive= new Salvo(1,gamePlayerTwo,locationSalvoFive);
//			salvoRepository.save(salvoFive);
//
//			List<String> locationSalvoSix = Arrays.asList("B5", "D5", "C7");
//			Salvo salvoSix= new Salvo(1,gamePlayerTwo,locationSalvoSix);
//			salvoRepository.save(salvoSix);
//
//			List<String> locationSalvoSeven = Arrays.asList("A3", "H6");
//			Salvo salvoSeven= new Salvo(2,gamePlayerTwo,locationSalvoSeven);
//			salvoRepository.save(salvoSeven);
//
//			List<String> locationSalvoHeight = Arrays.asList("C5", "C6");
//			Salvo salvoHeight= new Salvo(2,gamePlayerTwo,locationSalvoHeight);
//			salvoRepository.save(salvoHeight);
//
//			List<String> locationSalvoNine = Arrays.asList("G6", "H6", "A4");
//			Salvo salvoNine= new Salvo(1,gamePlayerThree,locationSalvoNine);
//			salvoRepository.save(salvoNine);
//
//			List<String> locationSalvoTen = Arrays.asList("H1", "H2", "H3");
//			Salvo salvoTen= new Salvo(1,gamePlayerThree,locationSalvoTen);
//			salvoRepository.save(salvoTen);
//
//			List<String> locationSalvoEleven = Arrays.asList("A2", "A3", "D8");
//			Salvo salvoEleven= new Salvo(2,gamePlayerThree,locationSalvoEleven);
//			salvoRepository.save(salvoEleven);
//
//			List<String> locationSalvoTwelve = Arrays.asList("E1", "F2", "G3");
//			Salvo salvoTwelve= new Salvo(2,gamePlayerThree,locationSalvoTwelve);
//			salvoRepository.save(salvoTwelve);
//
//			List<String> locationSalvoThirteen = Arrays.asList("A3", "A4", "F7");
//			Salvo salvoThirteen= new Salvo(1,gamePlayerFour,locationSalvoThirteen);
//			salvoRepository.save(salvoThirteen);
//
//			List<String> locationSalvoFourteen = Arrays.asList("B5", "C6", "H1");
//			Salvo salvoFourteen= new Salvo(1,gamePlayerFour,locationSalvoFourteen);
//			salvoRepository.save(salvoFourteen);
//
//			List<String> locationSalvoFifteen = Arrays.asList("A2", "G6", "H6");
//			Salvo salvoFifteen= new Salvo(2,gamePlayerFour,locationSalvoFifteen);
//			salvoRepository.save(salvoFifteen);
//
//			List<String> locationSalvoSixteen = Arrays.asList("C5", "C7", "D5");
//			Salvo salvoSixteen= new Salvo(2,gamePlayerFour,locationSalvoSixteen);
//			salvoRepository.save(salvoSixteen);
//
//			List<String> locationSalvoSeventeen = Arrays.asList("A1", "A2", "A3");
//			Salvo salvoSeventeen= new Salvo(1,gamePlayerFive,locationSalvoSeventeen);
//			salvoRepository.save(salvoSeventeen);
//
//			List<String> locationSalvoEighteen = Arrays.asList("B5", "B6", "C7");
//			Salvo salvoEighteen= new Salvo(1,gamePlayerFive,locationSalvoEighteen);
//			salvoRepository.save(salvoEighteen);
//
//			List<String> locationSalvoNineteen = Arrays.asList("G6", "G7", "G8");
//			Salvo salvoNineteen= new Salvo(2,gamePlayerFive,locationSalvoNineteen);
//			salvoRepository.save(salvoNineteen);
//
//			List<String> locationSalvoTwenty = Arrays.asList("C6", "D6", "E6");
//			Salvo salvoTwenty= new Salvo(2,gamePlayerFive,locationSalvoTwenty);
//			salvoRepository.save(salvoTwenty);
//
//			List<String> locationSalvoTwentyOne = Arrays.asList();
//			Salvo salvoTwentyOne = new Salvo(3,gamePlayerFive,locationSalvoTwentyOne);
//			salvoRepository.save(salvoTwentyOne);
//
//			List<String> locationSalvoTwentyTwo = Arrays.asList("H1", "H8");
//			Salvo salvoTwentyTwo = new Salvo(3,gamePlayerFive,locationSalvoTwentyTwo);
//			salvoRepository.save(salvoTwentyTwo);



			Score scoreOne= new Score (1, playerOne, gameOne);
			scoreRepository.save(scoreOne);
          
           Score scoreTwo =new Score (0, playerTwo, gameOne);
           scoreRepository.save(scoreTwo);

           Score scoreThree= new Score (0.5, playerOne, gameTwo);
           scoreRepository.save(scoreThree);

           Score scoreFour = new Score (0.5, playerTwo, gameTwo);
           scoreRepository.save(scoreFour);

           Score scoreFive = new Score(1, playerOne, gameThree);
           scoreRepository.save(scoreFive);

           Score scoreSix = new Score (0, playerFour, gameThree);
           scoreRepository.save (scoreSix);

           Score scoreSeven = new Score (0.5, playerTwo, gameFour);
			scoreRepository.save (scoreSeven);

			Score scoreHeight = new Score (0.5, playerOne, gameFour);
			scoreRepository.save (scoreHeight);

		};
	}


}
