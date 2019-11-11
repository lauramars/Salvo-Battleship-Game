console.log(moment().startOf('date').fromNow());

var gamesApp = new Vue({
   el: "#gamesApp",

   data: {
      game: [],
      games: [],
      id: [],
      gamePlayers: [],
      players: {},
      player: [],
      userName: "",
      password:"",
      date: [],
      actualDate:[],
      score:[],
      scores:[],
      totalScore:[],
      userScore:[],
      scoreArray:[],
      user:{},
      authenticated:false,
      
   },

   created() {
      this.gameData();
      this.scoreData();      
      
      },
   


   methods: {

      //-----------GAMES-----------

      async gameData() {
         let url = "/api/games"

         this.data = await fetch(url, {
            method: "GET",
            credentials: "include",
         })
            .then(response => response.json())
            .then(data => {
               console.log(data)
               return data.games;
            })
            .catch(error => console.log(error))

         this.games = this.data;
         console.log(this.games)

 this.gamePlayers=this.games.map(el=>el.gamePlayers)   
 console.log(this.gamePlayers);


 
 
 this.games.forEach(element => {
            console.log(this.timeConversion(this.getWhenGameStarted(element.date)));
         }); 

         this.games.forEach(element => {
         console.log((moment().startOf(element.date).fromNow()));
         
         
         })

      },

      getWhenGameStarted(date) {
         let currentTime = new Date().getTime();
         let gameDate = new Date(date).getTime();

         return currentTime - gameDate;
      },

      timeConversion(millisec) {

         var seconds = (millisec / 1000).toFixed(1);
 
         var minutes = (millisec / (1000 * 60)).toFixed(1);
 
         var hours = (millisec / (1000 * 60 * 60)).toFixed(1);
 
         var days = (millisec / (1000 * 60 * 60 * 24)).toFixed(1);
 
         if (seconds < 60) {
             return seconds + " Sec Ago";
         } else if (minutes < 60) {
             return minutes + " Min Ago";
         } else if (hours < 24) {
             return hours + " Hrs Ago";
         } else {
             return days + " Days Ago"
         }
     },

     // -------- SCORES ------------

      async scoreData(){
           let url= "/api/scores/"

           this.data = await fetch(url, {
            method: "GET",
            credentials: "include",
         })
            .then(response => response.json())
            .then(data => {
               return data
            })
            .catch(error => console.log(error))

         this.scores=this.data;
         this.win=this.scores.win;
         this.loss=this.scores.loss;
         this.tie=this.scores.tie;
         
      },

// --------------LOGIN AND LOGOUT-------------------

      async login(){
      fetch("/api/login", {
         credentials: 'include',
         headers: {
             'Content-Type': 'application/x-www-form-urlencoded'
         },
         method: 'POST',
         body: 'userName='+ gamesApp.userName + '&password='+ gamesApp.password,
         })
         .then(function (data) {
             console.log('Request success: ', data);
            gamesApp.authenticated = true;
         })
         .catch(function (error) {
             console.log('Request failure: ', error);
         });
   
         },
   

      async logout(){
         fetch("/api/logout", {
            credentials: 'include',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            method: 'POST',
        })
            .then(function (data) {
                console.log('Request success: ', data);
                gamesApp.authenticated = false;
            })
            .catch(function (error) {
                console.log('Request failure: ', error);
            });
      },

      async signUp(){

      fetch("/api/players", {
         credentials: 'include',
         headers: {
         'Content-Type': 'application/x-www-form-urlencoded'
         },
         method: 'POST',
         body: 'userName='+ "" + '&password='+ "",
         })
         .then(function (data) {
         console.log('Request success: ', data.text());
         }).then(function () {
         })
         .catch(function (error) {
         console.log('Request failure: ', error);
         });
      }
   }
})



   

   