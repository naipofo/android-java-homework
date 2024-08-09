const sqlite = require('sqlite3').verbose();
const path = require('path');
const db = new sqlite.Database("../baza.sqlite3", (err) => {
    if (err) {
      console.error(err.message)
      throw err
    }else{
        db.run(`
            CREATE TABLE reports(
                name TEXT,
                tags TEXT,
                date TEXT,
                content TEXT
            );`,
        (err) => {
            if (err) {
                // Table already created
            }else{
                // Table just created, creating some rows
                var insert = 'INSERT INTO reports (name, tags, date, content) VALUES (?,?,?,?)'
                db.run(insert, ["rep1","a,s,d","2020-02-02", "# tytul"])
            }
        });  
    }
});

module.exports = db
