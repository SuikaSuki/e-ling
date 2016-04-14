define("eling/component/ui/autocomplete/1.0.0/dist/autocomplete",["jquery/jquery-plugins/autocomplete/autocomplete","eling/component/core/uicomponent/2.0.0/src/uicomponent","base","tools","handlebars","theme","templatable","bootstrap","eling/component/utils/tools/tools","./autocomplete.tpl"],function(a,b,c){a("jquery/jquery-plugins/autocomplete/autocomplete");var d=a("eling/component/core/uicomponent/2.0.0/src/uicomponent"),e=a("eling/component/utils/tools/tools"),f=a("./autocomplete.tpl"),g=d.extend({attrs:{template:f,autoRender:!0,model:{id:null,name:null,className:null,style:null,url:null,params:null,keyField:"key",valueField:"value",disabled:!1,readonly:!1}},afterRender:function(){var a=this.get("model");this.initPlugin(a),this.setDisabled(a.disabled),this.setReadonly(a.readonly)},initPlugin:function(a){var b=this,c=this.$("input"),d={minChars:1,processData:function(b,c){var d=[];for(var f in b){var c=e._getValueFromObject(a.valueField,b[f]);d.push({value:a.format?a.format(b[f]):c,data:b[f]})}return d},showResult:function(b,c){return a.format?a.format(c):b},mustMatch:!0,maxItemsToShow:5,selectFirst:!0,autoFill:!1,selectOnly:!0,remoteDataType:"json"},f=$.extend(!0,d,a),g=a.params;"function"==typeof g&&(g=g()),f.extraParams=g,f.onItemSelect=function(c){b.setData(c.data),a.onItemSelect&&a.onItemSelect(c.data)},c.autocomplete(f)},setValue:function(a){var b=this.get("model"),c=null;c=b.format?b.format(a):b.valueField?e._getValueFromObject(b.valueField,a):a;var d=e._getValueFromObject(b.keyField,a);this.$("input").val(c).attr("data-key",d)},getValue:function(){return this.$("input").attr("data-key")},setData:function(a){this.set("data",a),this.setValue(a)},getData:function(){return this.get("data")},reset:function(){this.$("input").val("").removeAttr("data-key"),this.set("data",null);var a=this.get("model");this.setDisabled(a.disabled),this.setReadonly(a.readonly)},setDisabled:function(a){a?this.$("input").attr("disabled","disabled"):this.$("input").removeAttr("disabled")},setReadonly:function(a){a?this.$("input").attr("readonly","readonly"):this.$("input").removeAttr("readonly")},getInstance:function(){return this.$("input").data("autocompleter")}});c.exports=g}),define("eling/component/ui/autocomplete/1.0.0/dist/autocomplete.tpl",[],"<div class=\"el-autocomplete {{this.className}}\" style=\"{{this.style}}\">\n	<input id='{{this.id}}' name='{{this.name}}' type='text' placeholder ='{{this.placeholder}}' \n		class='form-control' value='{{this.value}}'  {{this.validate}}/>\n</div>\n\n");