
var gamesApp = new Vue({
   el: "#gamesApp",

   data: {
      game: [],
      games: [],
      id: [],
      gamePlayers: [],
      players: {},
      userName: "",
      date: [],
      actualDate:[],
      score:[],
      scores:[],
      totalScore:[],
      userScore:[],
      scoreArray:[],
   },

   created() {
      this.gameData();
      this.scoreData();
      
   },


   methods: {

      async gameData() {
         let url = "/api/games/"

         this.data = await fetch(url, {
            method: "GET",
            credentials: "include",
         })
            .then(response => response.json())
            .then(data => {
               console.log(data)
               return data
            })
            .catch(error => console.log(error))

         this.games = this.data;
         console.log(this.games)

         this.games.forEach(element => {
            console.log(element.date);
         }); 
       
         this.stringDate= this.games.forEach(element =>element.date).toLocaleString();

         console.log(this.stringDate)
      },


      async scoreData(){
           let url= "/api/scores/"

           this.data = await fetch(url, {
            method: "GET",
            credentials: "include",
         })
            .then(response => response.json())
            .then(data => {
               console.log(data)
               return data
            })
            .catch(error => console.log(error))

         this.scores=this.data;
         this.win=this.scores.win;
         this.loss=this.scores.loss;
         this.tie=this.scores.tie;
         
      },

   }
})



   

   