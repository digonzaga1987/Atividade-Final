// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
var pecuariaApp = angular.module("pecuariaApp",["ionic"]);

pecuariaApp.service("pecuariaService",["$http","$rootScope",pecuariaService]);

pecuariaApp.controller("pecuariaCtrl", ["$scope","$ionicLoading","pecuariaService",pecuariaCtrl]);

function pecuariaCtrl($scope, $ionicLoading, pecuariaService){
    $scope.animais = [];
    $scope.$on("pecuariaApp.pecuaria", function(_,result){
              result.data.forEach(
                   function (c){
                        $scope.animais.push({
                            nome: c.nome,
                            nomecompleto: c.nomecompleto,
                            rgd:  c.rgd,
                            rgn:  c.rgn,
                            datanasc: c.datanasc,
                            siglausual: c.siglausual,
                            seriealfa: c.seriealfa
                        });
              }
        );
         
     }
    
    );
    
    pecuariaService.loadpecuaria();
    
};


function pecuariaService($http,$rootScope){
    
    this.loadpecuaria = function(){
        $http.get("http://localhost:8080/CrudJSF/rest/animais").success(
            function(result){
                $rootScope.$broadcast("pecuariaApp.pecuaria",result);                  
                }            
            ).error(function(){ 
                     console.error("DEU PAU");
                     $rootScope.$broadcast("pecuariaApp.pecuaria","erro");   
                     //alert('Errro ao carregar a listagem de pecuaria');  
                     }
            );
        
    }
    
};