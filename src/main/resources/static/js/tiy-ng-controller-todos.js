angular.module('TIYAngularToDoApp', [])
   .controller('SampleController', function($scope, $http) {

        $scope.getToDo = function() {
            console.log("About to go get me some data!");

            $http.get("/todos.json")
                .then(
                    function successCallback(response) {
                        console.log(response.data);
                        console.log("Adding data to scope");
                        $scope.todos = response.data;
                    },
                    function errorCallback(response) {
                        console.log("Unable to get data");
                    });

        $scope.addToDo = function() {
            console.log("About to add the following todo " + JSON.stringify($scope.newToDo));

            $http.post("/addToDo.json", $scope.newToDo)
                .then(
                    function successCallback(response) {
                        console.log(response.data);
                        console.log("Adding data to scope");
                        $scope.todos = response.data;
                    },
                    function errorCallback(response) {
                        console.log("Unable to get data");
                    });
        };

        $scope.deleteToDo = function(todoID) {
        console.log("about to delete the folowing todo " + JSON.stringify(todoID));
            console.log("deleting to do");

            $http.get("/delete.json?todoID=" + todoID)
                .then(
                    function successDelete(response) {
                    alert("Deleting stuff!")
                        console.log(response.data);
                        $scope.todos = response.data;
                        },
                    function errorDelete(response) {
                        console.log("Unable to delete");
                        });
            };

        $scope.toggleToDo = function(todoID) {
            console.log("toggling");

            $http.get("/toggleToDo.json?todoID=" + todoID)
                .then(
                function successCallback(response) {
                console.log(response.data);
                $scope.todos = response.data;
                },
                function errorCallback(response) {
                console.log("Unable to toggle");
                }
                )};

        }
        $scope.getToDo();
        $scope.newToDo = {};

    });