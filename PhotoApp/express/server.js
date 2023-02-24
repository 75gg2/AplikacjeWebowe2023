var express = require('express');
var formidable = require('formidable');
const path = require('path')
const fs = require('fs');   
var app = express();

app.post('/', (req, res) => {
    const form = new formidable.IncomingForm();
    form.parse(req, function(err, fields, files){
        console.log(files);
        var oldPath = files.file.filepath;
        var newPath = path.join(__dirname, 'uploads')
                + '/'+Math.floor(Math.random()*10000)+files.file.originalFilename
                console.log(oldPath)
        var rawData = fs.readFileSync(oldPath)
      
        fs.writeFile(newPath, rawData, function(err){
            if(err) console.log(err)
            return res.send("Successfully uploaded")
        })
  })
});

app.listen(3000)
