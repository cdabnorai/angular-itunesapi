'use strict';

var app = angular.module('uiApp');

app.service('ItunesService', ['$http', function($http) {
    this.performSearch = function(searchRequest) {

      return $http.post('/api_itunes/rest/api/itunes/search', searchRequest);
    };
  }]);

app.controller('MainCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
  
app.controller('SearchController', ['$http', '$scope', 'ItunesService', function ($http, $scope, ItunesService) {

    $scope.searchRequest = {};
    
    $scope.mediaTypes = [
        {
          mediaName : 'All',
          mediaCode : 'all'
        },
        {
          mediaName : 'Movie',
          mediaCode : 'movie'
        },
        {
          mediaName : 'Podcast',
          mediaCode : 'movie'
        },
        {
          mediaName : 'Music',
          mediaCode : 'music'
        },
        {
          mediaName : 'Music Video',
          mediaCode : 'musicVideo'
        },
        {
          mediaName : 'Audiobook',
          mediaCode : 'audiobook'
        },
        {
          mediaName : 'Short Film',
          mediaCode : 'shortFilm'
        },
        {
          mediaName : 'TV Show',
          mediaCode : 'tvShow'
        },
        {
          mediaName : 'Software',
          mediaCode : 'software'
        },
        {
          mediaName : 'EBook',
          mediaCode : 'ebook'
        }
      ];
    $scope.searchRequest.selectedMedia = $scope.mediaTypes[0];

    $scope.performSearch = function(searchRequest) {

      if(typeof searchRequest !== 'undefined' && searchRequest.text && searchRequest.text !== '') {
        $scope.clear();
        $scope.searchInProgress = true;
        $scope.searchText = searchRequest.text;
        $scope.searchMedia = searchRequest.selectedMedia.mediaName;

        ItunesService.performSearch(searchRequest)
        .success( function(succ) {
          $scope.searchInProgress = false;
          $scope.searchResults = succ;
          $scope.searchError = undefined;
        })
        .error( function (error) {
          $scope.searchInProgress = false;
          $scope.searchError = error.message;
          $scope.searchResults = undefined;
        });
      } else {
        $scope.clear();
      }
    };
    
    $scope.clear = function() {
      $scope.searchText = undefined;
      $scope.searchMedia = undefined;
      $scope.searchResults = undefined;
      $scope.searchError = undefined;
      $scope.searchInProgress = undefined;
    };
  }]);
