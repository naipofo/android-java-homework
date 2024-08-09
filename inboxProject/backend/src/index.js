const express = require('express');
const bodyParser = require('body-parser');

const app = express();
const port = 3000 || process.env.PORT;
var db = require("./services/db.js")
app.use(function (req, res, next) {
    res.setHeader('Access-Control-Allow-Origin', '*');
    res.setHeader('Access-Control-Allow-Headers', '*');
    next();
});
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));


app.get('/', (req, res) => {
    res.json({message: 'alive'});
});


app.get('/list', (req, res) => {
    db.all(`SELECT rowid, name, tags, date FROM reports`, [], (e, r)=>{
        r.forEach(r => {
            r.tags = r.tags.split(",")
        });
        res.json({
            "message":"success",
            "data":r
        })
    })
});

app.post('/add', (req, res) => {
    var sql ='INSERT INTO reports (name, tags, date, content) VALUES (?,?,?,?)'
    console.log('Got body:', req.body);
    
    var params =[req.body.name, req.body.tags, req.body.date, req.body.content]
    db.run(sql, params, (e)=>{
        res.json({
            "message":`success ${e}`,
        })
    })
});

app.post('/edit/:id', (req, res) => {
    var sql ='UPDATE reports SET name = ?, tags = ?, date = ?, content = ? WHERE rowid = ?'
    var params =[req.body.name, req.body.tags, req.body.date, req.body.content, req.params.id]
    db.run(sql, params, (e)=>{
        res.json({
            "message":`success ${e}`,
        })
    })
});

app.get('/get/:id', (req, res) => {
    db.all(`SELECT rowid, name, tags, date, content FROM reports WHERE rowid = ?`, [req.params.id], (e, r)=>{
        r = r[0]
        r.tags = r.tags.split(",")
        res.json({
            "message":"success ${e}",
            "data":r
        })
    })
});

app.get('/delete/:id', (req, res) => {
    db.all(`DELETE FROM reports WHERE rowid = ?`, [req.params.id], (e, r)=>{
        res.json({
            "message":`success ${e}`,
        })
    })
});

app.listen(port, () => {
  console.log(`Example app listening at http://localhost:${port}`);
});