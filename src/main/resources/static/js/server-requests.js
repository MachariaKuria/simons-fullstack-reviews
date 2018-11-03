const tagAddButton = document.querySelector('.add-tag button');
const tagAddInput = document.querySelector('.add-tag input');
const tagsList = document.querySelector('.tags-list ul');
const tagRemoveButton = document.querySelector('.remove-tag button');
const tagRemoveInput = document.querySelector('.remove-tag input');

const xhr = new XMLHttpRequest();
xhr.onreadystatechange = function(){
    if(xhr.readyState == 4 && xhr.status == 200){
        const res = xhr.responseText;
        tagsList.innerHTML = res;
    }
}

tagAddButton.addEventListener('click',function(){
        postTags(tagsAddInput.value)
        tagAddInput.value = "";
});

function postTag(tagName){
    xhr.open('POST','/tags/' + tagName, true);
    xhr.send();
}

tagRemoveButton.addEventListener('click',function(){
    var ask = confirm('Are you sure you want to remove this tag?');

    if(ask == true) {
        removeTags(tagRemoveInput);
        tagRemoveInput.value="";
    }

});

function removeTags(tagName){
    xhr.open('POST','/tags/remove/' + tagName, true);
    xhr.send();
}
