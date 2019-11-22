function SubmitButtonClicked(){
	var title = document.getElementById('title').value;
	var image = document.getElementById('image').value;
	var price = document.getElementById('price').value;
	var date = document.getElementById('date').value;
	var power = document.getElementById('power').value;
	var euro = document.getElementById('euro').value;
	var mileage = document.getElementById('mileage').value;
	var color = document.getElementById('color').value;
	var brand = document.getElementById('brand').value;
	var model = document.getElementById('model').value;
	var region = document.getElementById('region').value;
	var category = document.getElementById('category').value;
	var transmission = document.getElementById('transmission').value;
	var engine = document.getElementById('engine').value;
	if (title.replace(/\s/g,"") == "") {
		alert("Моля въведете заглавие!!!");
		document.getElementById('title').value =  "";
		return false;
	}
	if (image.replace(/\s/g,"") == "") {
		alert("Моля въведете снимка!!!");
		document.getElementById('image').value =  "";
        return false;
	}
	if (price.replace(/\s/g,"") == "") {
		alert("Моля въведете цена!!!");
		document.getElementById('price').value =  "";
        return false;
	}
	if (date.replace(/\s/g,"") == "") {
		alert("Моля въведете година на производство!!!");
		document.getElementById('date').value =  "";
        return false;
	}
	if (power.replace(/\s/g,"") == "") {
		alert("Моля въведете мощност!!!");
		document.getElementById('power').value =  "";
        return false;
	}
	if (euro.replace(/\s/g,"") == "") {
		alert("Моля въведете евростандарт!!!");
		document.getElementById('euro').value =  "";
        return false;
	}
	if (mileage.replace(/\s/g,"") == "") {
		alert("Моля въведете изминати километри!!!");
		document.getElementById('mileage').value =  "";
        return false;
	}
	if (color.replace(/\s/g,"") == "") {
		alert("Моля въведете цвят на колата!!!");
		document.getElementById('color').value =  "";
        return false;
	}
	if (brand.replace(/\s/g,"") == "") {
		alert("Моля въведете марка на колата!!!");
		document.getElementById('brand').value =  "";
        return false;
	}
	if (model.replace(/\s/g,"") == "") {
		alert("Моля въведете модел на колата!!!");
		document.getElementById('model').value =  "";
        return false;
	}
	if (region.replace(/\s/g,"") == "") {
		alert("Моля въведете регион на колата!!!");
		document.getElementById('region').value =  "";
        return false;
	}
	if (category.replace(/\s/g,"") == "") {
		alert("Моля въведете категория!!!");
		document.getElementById('category').value =  "";
        return false;
	}
	if (transmission.replace(/\s/g,"") == "") {
		alert("Моля въведете предавка!!!");
		document.getElementById('transmission').value =  "";
        return false;
	}
	if (engine.replace(/\s/g,"") == "") {
		alert("Моля въведете тип на двигателя!!!");
		document.getElementById('engine').value =  "";
        return false;
	}


}