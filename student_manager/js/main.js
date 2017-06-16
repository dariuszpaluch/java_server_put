"use strict";

var API = "http://localhost:9995/";

var prepareModel = function (url, idColumnName) {
  var self = ko.observableArray();

  self.url = url;
  self.idColumnName = idColumnName || "id";

  self.get = function (query) {
    var url = API + self.url;

    if(query) {
      url += query;
    }

    $.ajax({
      type: 'GET',
      url: url,
      contentType: 'application/json; charset=UTF-8',
      dataType: "json",
      success: function (data) {
        if(self.sub) {
          self.sub.dispose();
        }
        self.removeAll();
        data.forEach(function (item) {
          const object = ko.mapping.fromJS(item, { ignore: ["link"] });

          object.links = [];
          if($.isArray(item.link)) {
            item.link.forEach(function(link) {
              object.links[link.params.rel] = link.href;
            });
          } else {
            object.links[item.link.params.rel] = item.link.href;
          }



          self.push(object);

          console.log("DAREK");
          ko.computed(function() {
            return ko.toJSON(object);
          }).subscribe(function() {
            self.updateRequest(object);
          });

        });

        self.sub = self.subscribe(function(changes) {
          console.log(changes);
          console.log("TUTAJ");
          changes.forEach(function(change) {
            switch(change.status) {
              case "deleted": {
                console.log("DELETED");
                self.deleteRequest(change.value);
                break;
              }
              case "added": {
                console.log("ADDED");
                self.saveRequest(change.value);
                break;
              }
            }
          });
        }, null, "arrayChange");



      },
      error: function (error) {
        console.error(error);
      }
    });
  };

  self.add = function(form) {
    var data = {};

    $(form).serializeArray().map(function (property) {
      if(property.name !== 'index') {

        data[property.name] = property.value;
      }
    });

    $(form).find("tbody").filter(function () {
      return $(this).hasClass('edit');
    }).find("input").each(function () {

      $(this).val('');
    });

    data[self.idColumnName] = null;

    const observableData = ko.mapping.fromJS(data);

    self.push(observableData);
  };


  self.updateRequest = function(object) {
      $.ajax({
      url: object.links['self'],
      dataType: "json",
      contentType: "application/json",
      data: ko.mapping.toJSON(object, { ignore: ["links"] }),
      method: "PUT",

      error: function(error) {
          // alert("Źle wypełnione dane, Proszę popraw");
      }
    });
  }

  self.saveRequest = function(object) {
    console.log('save', object);
    console.log(self.url);

    $.ajax({
      url: API + self.url,
      contentType: 'application/json; charset=UTF-8',
      dataType: "json",
      data: ko.mapping.toJSON(object),
      method: "POST",
      success: function(data) {
          var response = ko.mapping.fromJS(data);
          object[self.idColumnName](response[self.idColumnName]());

          object.links = [];

          if($.isArray(data.link)) {
              data.link.forEach(function(link) {

                  object.links[link.params.rel] = link.href;
              });
          } else {
              object.links[data.link.params.rel] = data.link.href;
          }

          ko.computed(function() {
              return ko.toJSON(object);

          }).subscribe(function() {
              self.updateRequest(object);
          });

      },
      error: function(error) {
      //     alert("Źle wypełnione dane, Proszę popraw");
      }
    });
  };


  self.deleteRequest = function(object) {
    $.ajax({
      url: object.links.self,
      method: "DELETE"
    });
  };

  self.delete = function() {

    self.remove(this);
  };


  self.parseQuery = function() {
    var queries = {};
    Object.keys(self.queryParams).forEach(function(key) {
      if(self.queryParams[key]()) {

        queries[key] = self.queryParams[key];
      }
    });

    console.log(queries);
    self.get('?' + $.param(ko.mapping.toJS(queries)));
  };


  return self;
};

function studentManagerViewModel() {
  var self = this;
  self.students = prepareModel("students", "index");
  self.students.showGrades = function() {
    var index = this.index();
    var firstName = this.firstName();
    var lastName = this.lastName();




    self.grades.studentIndex = index;
    self.grades.firstName(firstName);
    self.grades.lastName(lastName);
    self.grades.index(index);
    self.grades.url = "students/" + index + "/grades";
    self.grades.get();

    window.location = "#grades-table";
  };

  self.students.queryParams = {
    indexQuery: ko.observable(),
    firstNameQuery: ko.observable(),
    lastNameQuery: ko.observable(),
    dateOfBirthQuery: ko.observable()
  };

  Object.keys(self.students.queryParams).forEach(function(key) {
    self.students.queryParams[key].subscribe(function() {
      self.students.parseQuery();
    });
  });

  self.students.get();

  self.courses = prepareModel("courses");
  self.courses.queryParams = {
    nameQuery: ko.observable(),
    teacherQuery: ko.observable()
  };

  Object.keys(self.courses.queryParams).forEach(function(key) {
    self.courses.queryParams[key].subscribe(function() {
      self.courses.parseQuery();
    });
  });
  self.courses.get();

  self.grades = prepareModel();

    self.grades.firstName = ko.observable('');
    self.grades.lastName = ko.observable('');
    self.grades.index = ko.observable(1);

  self.grades.add = function(form) {
    var data = {};
    $(form).serializeArray().map(function(property) {

      if(property.name === 'course') {
        data.course = {
          id: property.value,
          name: null
        }
      } else {
        data[property.name] = property.value;

      }
    });

    data['id'] = null;

    $(form).find("tbody").filter(function () {
      return $(this).hasClass('edit');
    }).find("input").each(function () {
      $(this).val('');
    });

    data['id'] = null;

    const observableData = ko.mapping.fromJS(data);

      self.grades.push(observableData);
  };


  self.grades.queryParams = {
    valueQuery: ko.observable(),
    courseNameQuery: ko.observable(),
    createdQuery: ko.observable(),
  };

  Object.keys(self.grades.queryParams).forEach(function(key) {
    self.grades.queryParams[key].subscribe(function() {

      self.grades.parseQuery();
    });
  });
}


$(document).ready(function () {

  ko.applyBindings(new studentManagerViewModel());
});
