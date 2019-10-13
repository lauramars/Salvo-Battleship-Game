console.log("hello")

let date=moment().startOf('hour').fromNow()
console.log(date)

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
   },

   created() {
      this.gameData();
      
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
  
         
      },

      getTime(){
         return date=moment().startOf('hour').fromNow()
         
      }

   }
})



   

   