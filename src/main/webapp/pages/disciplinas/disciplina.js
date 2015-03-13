module = angular.module("Prova", []);

module.controller("DisciplinaController", ["$scope","$http", DisciplinaController]);


function DisciplinaController($scope,$http) {
    
    $scope.iniciar = funcaoIniciar;
    $scope.salvar = funcaoSalvar;
    $scope.excluir = funcaoExcluir;
    $scope.editar = funcaoEditar;
    
    $scope.disciplinas = [];
    $scope.disciplina = {};
    $scope.isNovo = true;
    
    function funcaoEditar(vitima) {
        $scope.disciplina = angular.copy(vitima);
        $scope.isNovo = false;
        $http.put("/disciplinas", $scope.vitima).success(onSuccess).error(onError);
    }

    
    function funcaoExcluir(vitima) {
        $http.delete("/disciplinas" + $scope.vitima.id, $scope.vitima).success(onSuccess).error(onError);
    }
    
    function funcaoSalvar() {
        if ($scope.isNovo){ 
        $http.post("/disciplinas", $scope.disciplina).success(onSuccess).error(onError);
    } else {
        $http.put("/disciplinas", $scope.disciplina.id, $scope.disciplina).success(onSuccess).error(onError);
    } 
 }
    
    function funcaoCarregar() {
        $http.get("/disciplinas").success(onSuccess).error(onError);   
        
        function onSuccess(data, status) {
            $scope.disciplinas = data;       
            console.log(data);
        }
        function onError(data, status) {
            alert("Deu erro: " + data);
        }
    }
    
    function funcaoIniciar() {
        funcaoCarregar();
        console.log(">>> disciplinas carregadas....");
    }
        
}



