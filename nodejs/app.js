const express = require('express');
const fs = require('fs');
const flag = require('./flag')
const app = express();

app.get('/flag', function (req, res) {
    function getflag(flag) {
      res.send(flag);
    }
    let delay = 10 * 1000;
    if (Number.isInteger(parseInt(req.query.delay))) {
      delay = Math.max(delay, parseInt(req.query.delay));
    }
    const t = setTimeout(getflag, delay,flag);
    setTimeout(() => {
      clearTimeout(t);
      try {
        res.send('Timeout!');
      } catch (e) {
      }
    }, 1000);
});
app.get('/', function (req, res) {
  res.set('Content-Type', 'text/javascript;charset=utf-8');
  res.send(fs.readFileSync('./app.js'));
});

app.listen(3000, '0.0.0.0', () => {
  console.log('Start listening')
});

//flag.json
{
    "FLAG":"flag{nice_to_meet_uo}"
}
