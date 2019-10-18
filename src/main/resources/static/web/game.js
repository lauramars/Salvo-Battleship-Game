console.log("ships where?!")

var gridApp= new Vue ({
    el: "#gridApp",

    data:{

        letters:["A", "B", "C", "D", "E", "F", "G", "H", "I", "J"],
        numbers:[1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
        currentPlayer:{},
        enemyPlayer: {},
        userShips:[],
        game: {},
        salvoes: [],
        allShipsLocation: [],
        allSalvosUserLocation:[],
        userSalvos:[],
        turn:[],
        
    },


    created(){
        this.gridData();
    },


    methods: {

        async gridData(){
             let url = "/api/games_view/" + location.search.split("=")[1]
             
            
        this.data= await fetch (url, {
            method:"GET",
            credentials: "include",
        })

        .then(response=> response.json())
        .then(data=> {
            console.log(data)
            return data
        })
        .catch(error => console.log(error))
      
        this.currentPlayer=this.data.currentPlayer;
        this.userShips=this.data.userShips;
        this.userSalvos=this.data.userSalvos;
        this.game = this.data.game;
        this.enemyPlayer= this.game.gamePlayers.filter(el=>el.players.userName != this.currentPlayer.userName)
        this.turn=this.userSalvos.map(el=>el.turn);

        

        this.createAllShipLocationArray();
        this.createAllSalvoUserLocationArray();
        this.getTurn();
    
    },

    // ???
    getTurn(){
        
        for (let i=0; i<this.turn.length; i++)
        {
            return this.allSalvosUserLocation.push(this.turn[i])
        }

        // console.log(this.turn[i])
        

    },

    addEnemyCell(cellId) {

        if (this.allSalvosUserLocation.includes(cellId)){
            return `missed-location` 
        }
    },
  

    addCell(cellId){

    if (this.allShipsLocation.includes(cellId)) {
            return `ship-location`
        }
    },


    createAllShipLocationArray() {
        //  let allShipsLocation; 
        
        for (let i=0; i<this.userShips.length; i++){
        var ship=this.userShips[i]
    
        for (let j=0; j<ship.location.length; j++){

        console.log(ship.location[j])
            this.allShipsLocation.push(ship.location[j]);
        }
    } 
        // return allShipsLocation;
    },


    createAllSalvoUserLocationArray(){
        // let allSalvosUserLocation;

      for (let i=0; i<this.userSalvos.length; i++){

        var salvo=this.userSalvos[i]

        for (let j=0; j<salvo.locations.length; j++){
            
            console.log(salvo.locations[j])

            this.allSalvosUserLocation.push(salvo.locations[j]);
        }
      }
        // return allSalvosUserLocation;
    },
       
    }
})

