const express = require("express")
const app = express()
const PORT = process.env.PORT || 3005

app.get("*", (req,res)=>{
    console.log(req)
    res.end()
})


app.listen(PORT, function () {
    console.log("start serwera na porcie " + PORT)
})

