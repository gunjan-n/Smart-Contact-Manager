document.querySelector("#image_input").addEventListener('change', function(event){
    let file = event.target.file[0];
    let reader = new FileReader();
    reader.onload = function(){
        document.getElementById("image_preview").setAttribute("src", reader.result);
    }
    reader.readAsDataURL(file);
})