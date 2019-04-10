if (window.console) {
  console.log("Welcome to your Play application's JavaScript!");
  // Get the modal
  var modal = document.getElementById('myModal');

  // Get the button that opens the modal
  var btn = document.getElementById("myBtn");

  // Get the <span> element that closes the modal
  var span = document.getElementsByClassName("close")[0];


  document.getElementById("myBtn").addEventListener("click", onclick);


  // When the user clicks on the button, open the modal 
  onclick = function () {
    console.log("click");
    modal.style.display = "block";
  }

  

  // When the user clicks on <span> (x), close the modal
  span.onclick = function () {
    modal.style.display = "none";
  }

  // When the user clicks anywhere outside of the modal, close it
  window.onclick = function (event) {
    if (event.target == modal) {
      modal.style.display = "none";
    }
  }
}