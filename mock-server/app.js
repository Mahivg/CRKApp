var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');
var bodyParser = require('body-parser');
var express = require('express');
var app = express();

app.use(logger('dev', {immediate: true}));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

app.post('/login', function(req, res) {
  if (req.params.mobile === "123" && req.params.password === "dev") {
    res.json({"auth_token":"aldjflakdsjfklasdjlfkasjd","name":"Vignesh","mobile_number":"9838383838","gender":"Male","blood_group":"O +ve","work_location":"CHennai","home_location":"Chennai","user_role":"donor/organizer"});
  } else {
    res.status(403).end();
  }
});


app.post("/Doner/Register", function(req, res) {
  if (req.body.name=== "Vignesh") {
    res
    .status(200)
    .json({"auth_token":"aldjflakdsjfklasdjlfkasjd","name":"Vignesh","mobile_number":"9838383838","gender":"Male","blood_group":"O +ve","work_location":"CHennai","home_location":"Chennai","user_role":"donor/organizer"})
    .end();
  } else {
    res.status(400).end();
  }
});

app.post("/user/:mobile_number", function(req, res) {
  if (req.param.mobile_number === "32322232322" && req.body.name === "Vignesh") {
    res.status(200).end();
  } else {
    res.status(400).end();
  }
});

app.get("/donation-requests", function (req, res) {
  res.json([{"donation_request_id":"some_unique_id_1","blood_group":"O+ve","patient_name":"Mani","Attender_name":"Gani","mobile_number":"42323232323","alternate_mobile_number":"434534343343","gender":"male","hospital_name":"Egmore Govt hospital","number_of_units":4,"cause":"cancer fighting patient","required_date":"2015-JUL-20","required_time":"13:10:00","is_verified":true,"organizer_id":"23222366564"},{"donation_request_id":"some_unique_id_2","blood_group":"O+ve","patient_name":"Mani","Attender_name":"Gani","mobile_number":"42323232323","alternate_mobile_number":"434534343343","gender":"male","hospital_name":"Egmore Govt hospital","number_of_units":4,"cause":"cancer fighting patient","required_date":"2015-JUL-20","required_time":"13:10:00","is_verified":false,"organizer_id":"2323244223233"}]);
})

app.post("/donation-requests", function (req, res) {
  if (req.body.patient_name=== "Mani") {
    res.status(200).end();
  } else {
    res.status(400).end();
  }
})

app.post("/donation-requests/:donation_request_id/verify", function (req, res) {
  if (req.params.donation_request_id=== "some_unique_id_1") {
    res.status(200).end();
  } else {
    res.status(400).end();
  }
})

app.post("/donation-requests/:donation_request_id/assign", function (req, res) {
  if (req.params.donation_request_id=== "some_unique_id_1" && req.body.organizer_id === "23222366564") {
    res.status(200).end();
  } else {
    res.status(400).end();
  }
})

app.post("/donation-requests/:donation_request_id/donate", function (req, res) {
  if (req.params.donation_request_id=== "some_unique_id_1" && req.body.donor_id === "9838383838") {
    res.status(200).end();
  } else {
    res.status(400).end();
  }
})

app.delete("/donation-requests/:donation_request_id", function (req, res) {
  if (req.params.donation_request_id=== "some_unique_id_1") {
    res.status(200).end();
  } else {
    res.status(400).end();
  }
})

app.get("/donation-requests/:donation_request_id/responses", function (req, res) {
  if (req.params.donation_request_id=== "some_unique_id_1") {
    res.json([{"name":"Vignesh","mobile_number":"9838383838","gender":"Male","blood_group":"O +ve","work_location":"CHennai","home_location":"Chennai","last_donated_date":"2015-jul-15"},{"name":"Vignesh","mobile_number":"9838383838","gender":"Male","blood_group":"O +ve","work_location":"CHennai","home_location":"Chennai","last_donated_date":"2015-jul-15"}]);
  } else {
    res.status(400).end();
  }
})

app.post("/donation-requests/:donation_request_id/responses", function (req, res) {
  if (req.params.donation_request_id=== "some_unique_id_1" && req.body.donor_id === "9838383838" && req.body.accept === true) {
    res.status(200).end();
  } else {
    res.status(400).end();
  }
})

app.get("/organizers", function (req, res) {
  res.json([
  {
    "name": "Vignesh",
    "mobile_number": "9838383838",
    "gender": "Male",
    "blood_group": "O +ve",
    "work_location": "CHennai",
    "home_location": "Chennai"
  }
  ]);
})

app.get("/search", function (req,res) {
  console.log(req.query);
  if (req.query.blood_group === "O+ve") {
    res.json ([
  {
    "name": "Vignesh",
    "mobile_number": "9838383838",
    "gender": "Male",
    "blood_group": "O +ve",
    "work_location": "CHennai",
    "home_location": "Chennai"
  }
]);
  } else {
    res.json([]);
  }
})

app.get("/logout", function (req, res) {
  res.status(200).end();
})

// catch 404 and forward to error handler
app.use(function(req, res, next) {
  var err = new Error('Not Found');
  err.status = 404;
  next(err);
});
// error handlers

// development error handler
// will print stacktrace
if (app.get('env') === 'development') {
  app.use(function(err, req, res, next) {
    res.status(err.status || 500);
    res.render('error', {
      message: err.message,
      error: err
    });
  });
}

module.exports = app;
