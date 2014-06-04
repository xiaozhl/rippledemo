var adv_index=1;
function closeAdv(id)
{
 $("#"+id).hide();
}

function changeAdv(oper)
{
 var adv = $("#adv");
 if(oper == '+')
 {
  adv_index++;
   if(adv_index >3){
   adv_index=1;
   }
 }
 else{
   adv_index--;
   if(adv_index ==0){
   adv_index=3;
   }
  }
  adv.hide();
  adv.css({"background":"url('img/ad"+adv_index+".jpg') no-repeat"});
  adv.fadeIn();
  
}

/** @serializedParams looks like "prop1=value1&prop2=value2".  
Nested property like 'prop.subprop=value' is also supported **/
function paramString2obj (serializedParams) {
	
	var obj={};
	function evalThem (str) {
		var attributeName = str.split("=")[0];
		var attributeValue = str.split("=")[1];
		if(!attributeValue){
			return ;
		}
		
		var array = attributeName.split(".");
		for (var i = 1; i < array.length; i++) {
			var tmpArray = Array();
			tmpArray.push("obj");
			for (var j = 0; j < i; j++) {
				tmpArray.push(array[j]);
			};
			var evalString = tmpArray.join(".");
			// alert(evalString);
			if(!eval(evalString)){
				eval(evalString+"={};");				
			}
		};
    
		eval("obj."+attributeName+"='"+attributeValue+"';");
		
	};

	var properties = serializedParams.split("&");
	for (var i = 0; i < properties.length; i++) {
		evalThem(properties[i]);
	};

	return obj;
}


$.fn.form2json = function(){
	var serializedParams = this.serialize();
	var obj = paramString2obj(serializedParams);
	return JSON.stringify(obj);
}

$.fn.serializeObject = function() {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [ o[this.name] ];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
}

function toUtf8(str){   
    var out, i, len, c;   
    out = "";   
    len = str.length;   
    for(i = 0; i < len; i++) {   
    	c = str.charCodeAt(i);   
    	if ((c >= 0x0001) && (c <= 0x007F)) {   
        	out += str.charAt(i);   
    	} else if (c > 0x07FF) {   
        	out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));   
        	out += String.fromCharCode(0x80 | ((c >>  6) & 0x3F));   
        	out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));   
    	} else {   
        	out += String.fromCharCode(0xC0 | ((c >>  6) & 0x1F));   
        	out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));   
    	}   
    }   
    return out;   
}

