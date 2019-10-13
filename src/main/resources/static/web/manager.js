console.log("hello world ")

var manager=new Vue({
    el:"#manager",
   
    data:{
        embedded:[],
        players:[],
        userName:"",

    },

    created(){
        this.loadData();
        
    },

    methods:{
        
        async loadData(){
            var url="/players/"
            
            this.data=await fetch(url,{
                method:"GET",
                credentials: "include",
            })
            .then(response=>response.json())
            .then(data=>{
                console.log(data)
                return data.players
            })
            .catch(error=>console.log(error))
            
        },

        addPlayer(){
            this.players.push(this.userName)
        },


        // async postPlayer(){
        //     this.data=await fetch (url,{
        //         method:"POST",
        //         headers: new Headers({
        //            dataType:"text",
        //            url:"/players/"
        //         })
        //     })


        // }
    },

})