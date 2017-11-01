"use strict";
console.log("Im here");
$( document ).ready(function(){
  $(".dropdown-button").dropdown();
  $('select').material_select();
  function confirmDelete(e) {
    console.log("clicking");
    $(e.target).innerHTML = `<button type="button"
                    class="waves-effect waves-light red lighten-1 btn"
                    name="deleteId"
                    value="${ad.id}">Delete</button>`
  }
  $(".deleteBtn").on("click", confirmDelete);
  $(".deleteBtn").on("click", function() {
    console.log("clicked");
  });
  $(".deleteBtn").on("hover", function(){
    console.log("hover")
  });



})();