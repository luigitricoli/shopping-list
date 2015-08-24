angular.module("shoppingLists").controller("shoppingListsCtrl", function ($scope, $http) {
    $scope.app = "Lista de Compras"
    $scope.lists = [];

    var normalizeParam = function(name){
        return name.replace(/ /g, '-');;
    }

    var loadLists = function (callback) {
        $http.get("${shopping.list.uri.domain}/api/v1/shopping-lists/names").success(function (data) {
            $scope.lists = data;
            callback(data);
        }).error(function (data, status) {
            $scope.message = "Aconteceu um problema: " + data;
        });
    }

    $scope.loadList = function (name) {
        $http.get("${shopping.list.uri.domain}/api/v1/shopping-list/" + normalizeParam(name)).success(function (data) {
            $scope.currentList = data;
            $scope.currentList.total = 0;
            if($scope.currentList.itens){
                $scope.currentList.itens.forEach(function(item){
                    var total = item.quantity * item.value;
                    $scope.currentList.total += total;
                });
            }
        }).error(function (data, status) {
            $scope.message = "Aconteceu um problema: " + data;
        });
    }

    var initialize = function () {
        if(!$scope.hasOwnProperty('initialized') || !$scope.initialized){
            loadLists(function(lists){
                $scope.loadList(lists[0]);
                $scope.initialized = true;
            });
        }
    }

    $scope.orderItemBy = function (field) {
        $scope.orderCriteria = field;
        $scope.orderCriteriaDirection = !$scope.orderCriteriaDirection;
    };

    $scope.createList = function (list) {
        $http.post("${shopping.list.uri.domain}/api/v1/shopping-lists/", list).success(function (data) {
            delete $scope.list;
            loadLists();
        });
    };

    $scope.removeList = function (name) {
        $http.delete("${shopping.list.uri.domain}/api/v1/shopping-list/" + normalizeParam(name)).success(function (data) {
            loadLists();
            $scope.loadList(name);
        });
    };

    $scope.addItem = function (list, item) {
        var target = {
            name: list.name,
            itens: [item]
        }
        console.log(target);

        var normalizedName = normalizeParam(target.name);
        $http.post("${shopping.list.uri.domain}/api/v1/shopping-list/" + normalizedName + "/itens", target).success(function (data) {
            $scope.itemForm.$setPristine();
            $scope.loadList(normalizedName);
            delete $scope.item;
        });
    };

    $scope.removeItem = function (list, item) {
        var normalizedName = normalizeParam(list.name);
        var normalizedLabel = normalizeParam(item.label);
        $scope.itemForm.$setPristine();
        $http.delete("${shopping.list.uri.domain}/api/v1/shopping-list/" + normalizedName + "/iten/" + normalizedLabel + "?value=" + item.value).success(function (data) {
            $scope.loadList(normalizedName);
            $scope.itemForm.$setPristine();
            delete $scope.item;
        });
    };

    $scope.buyItem = function (list, item) {
        item.purchased = true;
        var target = {
            name: list.name,
            itens: [item]
        }

        var normalizedName = normalizeParam(target.name);
        var normalizedLabel = normalizeParam(item.label);
        $http.put("${shopping.list.uri.domain}/api/v1/shopping-list/" + normalizedName + "/iten/" + normalizedLabel, target).success(function (data) {
            $scope.loadList(normalizedName);
            $scope.itemForm.$setPristine();
        });
    };

    $scope.hasSelectedItens = function (list) {
        return list.itens.some(function (item) {
            return item.selected;
        });
    };

    $scope.buyItens = function (list) {
        var selecteds = list.itens.filter(function (item) {
            if (item.selected) {
                item.purchased = true;
                delete item.selected;
                return item;
            }
        });
        var target = {
            name: list.name,
            itens: selecteds
        }
        var normalizedName = normalizeParam(target.name);
        $http.put("${shopping.list.uri.domain}/api/v1/shopping-list/" + normalizedName + "/itens/", target).success(function (data) {
            $scope.loadList(normalizedName);
        });
    };        

    initialize();
});