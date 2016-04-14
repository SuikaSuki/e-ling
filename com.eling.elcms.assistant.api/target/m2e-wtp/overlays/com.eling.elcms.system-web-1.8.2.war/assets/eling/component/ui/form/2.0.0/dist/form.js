define("eling/component/ui/form/2.0.0/dist/form",["eling/component/core/uicomponent/2.0.0/src/uicomponent","base","tools","handlebars","theme","templatable","bootstrap","gallery/handlebars/2.0.0/handlebars-seajs","./formutils","jquery/jquery-plugins/validate/jquery.validate","./formvalidate","./form.css","./plugins/date","jquery/jquery-plugins/datetimepicker/jquery.datetimepicker","./plugins/date.tpl","./plugins/place","jquery/jquery-plugins/select2/select2","eling/component/utils/ajaxwrapper","dialog","store","./plugins/place1","./plugins/place1.tpl","eling/component/ui/admindivision/1.0.0/dist/admindivision","./plugins/select","./plugins/select.tpl","./plugins/text","./plugins/text.tpl","./plugins/textarea","./plugins/textarea.tpl","./plugins/checkradio","./plugins/checkradio.tpl","./plugins/autocomplete","jquery/jquery-plugins/autocomplete/autocomplete","./form.tpl"],function(a,b,c){var d=a("eling/component/core/uicomponent/2.0.0/src/uicomponent"),e=a("gallery/handlebars/2.0.0/handlebars-seajs"),f=a("./formutils");a("jquery/jquery-plugins/validate/jquery.validate"),a("./formvalidate"),a("./form.css");var g=a("./plugins/date"),h=a("./plugins/place"),i=a("./plugins/place1"),j=a("./plugins/select"),k=a("./plugins/text"),l=a("./plugins/textarea"),m=a("./plugins/checkradio"),n=a("./plugins/autocomplete"),o={hidden:k,password:k,text:k,textarea:l,checklist:m,radiolist:m,select:j,date:g,place:h,place1:i,autocomplete:n};e.registerHelper("form_2_0_0",function(a,b){var c=a,d="",e=c.type||"text";return d+=c.format?c.format():o[e].getTemplate(c)});var p=d.extend({attrs:{template:a("./form.tpl"),autoRender:!0,saveaction:function(){return!1},itemValidates:{},model:{defaultButton:!0,saveText:"保存",cancelText:"取消",validate:{position:"right"}}},initCustAttr:function(a){for(var b=this.get("model")||{},c=b.id,d=a||b.items||[],e=this.get("itemValidates"),f=0;f<d.length;f++){d[f].form=c;var g=d[f].name?d[f].name.replace(/\./g,"-"):"";d[f].type||(d[f].type="text"),d[f].show="hidden"==d[f].type||d[f].show===!1?"hidden":"",d[f].fid="J-form-"+b.id+"-"+d[f].type+"-"+g;var h=d[f].validate||[],i=d[f].exValidate;if("function"==typeof i){var j=c+"-customer-valid-"+d[f].name.replace(/\./g,"-");h.push(j),e[j]=i}for(var k=0;k<h.length;k++)"required"==h[k]&&(d[f].isRequired=!0)}b.theme=b.theme.bgColor},events:function(a){for(var b={},c=this.get("model"),d=a||c.items||[],e=0;e<d.length;e++){var f=d[e].type;"function"==typeof o[f].events&&(b=$.extend(!0,b,o[f].events(d[e],this)))}return b["click .J-form-"+c.id+"-cancel"]=this.get("cancelaction")||function(){return!1},b},_initValidate:function(){var a=this.get("model"),b=a.id,c=this.get("saveaction"),d=a.validate.position,e=this.get("itemValidates");for(var f in e)!function(a,b){var c=b.toString(),d=c.match(/return ".*"/)[0],e=d.substring(7,d.length-1);$.validator.addMethod(a,function(a){return b(a)===!0?!0:!1},e)}(f,e[f]);$().validate&&this.element.find("form").validate({submitHandler:function(a){return c(),!1},errorElement:"span",errorClass:"help-block has-error",errorPlacement:function(a,c){return"right"==d?c.parents(".form-group").first().append(a):(a.css({position:"absolute"}),$("#"+b+" .form-group").css({"margin-bottom":"30px"}),c.after(a))},highlight:function(a){return $(a).closest(".form-group").removeClass("has-error has-success").addClass("has-error")},success:function(a){var c=a.closest(".form-group").removeClass("has-error").find(".help-block").remove();return"bottom"==d&&0==$("#"+b+" .help-block").length&&$("#"+b+" .form-group").css({"margin-bottom":"15px"}),c}})},_initPlugins:function(a,b){for(var c=this.get("model")||{},d=a||c.items||[],e=0;e<d.length;e++)!function(a,b,c){var d=b[c].type;"function"==typeof o[d].initPlugin&&o[d].initPlugin(a,b,b[c])}(b||this.element,d,e)},afterRender:function(){this._initValidate(),this._initPlugins()},getItemConfig:function(a,b){var c=this.get("model")||{},d=b||c.items||[];if("string"!=typeof a)return d[a];for(var e=0;e<d.length;e++)if(d[e].name==a)return d[e]},getValue:function(a){var b=this.getItemConfig(a);return o[b.type].getValue(b,this.element)},setValue:function(a,b){var c=this.getItemConfig(a);return o[c.type].setValue(b,c,this.element)},getData:function(a,b){if(a){var c=this.getItemConfig(a),d=c.type||"text";return o[d].getData(b,c,this.element)}for(var e={},f=this.get("model"),g=f.items,h=0;h<g.length;h++){var i=g[h].name,d=g[h].type||"text",j=o[d].getValue(this.getItemConfig(i),this.element);e[i]=j}return e},setLabel:function(a,b){var c=this.getItemConfig(a);this.$(".J-form-label-"+c.name).text(b)},getLabel:function(a){var b=this.getItemConfig(a);return this.$(".J-form-label-"+b.name).text()},setData:function(a,b,c){if(a.constructor===String){var d=this.getItemConfig(a),e=d.type;"function"==typeof o[e].setData&&o[e].setData(b,d,this.element)}else{c=b,b=a;for(var g=this.get("model")||{},h=c||g.items||[],i=0;i<h.length;i++)o[h[i].type].setValue(f._getValue(h[i].name,b),h[i],this.element)}},load:function(a,b){var c=this.getItemConfig(a),d=c.type||"text";"function"==typeof o[d].load&&o[d].load(b,c,this.element)},reset:function(a){for(var b=this.get("model")||{},c=a||b.items||[],d=0;d<c.length;d++){var e=c[d].type||"text";"function"==typeof o[e].reset&&o[e].reset(c[d],this.element),c[d].show?this.hide(c[d].name):this.show(c[d].name)}this.setDisabled(!1),this.element.find(".help-block").remove(),this.element.find(".has-error").removeClass("has-error")},setAttribute:function(a,b,c){var d=this.getItemConfig(a),e=d.type||"text";"function"==typeof o[e].setAttribute?o[e].setAttribute(d,b,c):this.$("."+d.fid).attr(b,c)},removeAttribute:function(a,b){var c=this.getItemConfig(a),d=c.type||"text";"function"==typeof o[d].removeAttribute?o[d].removeAttribute(c,b):this.$("."+c.fid).removeAttr(b)},setDisabled:function(a){a?(this.$("#"+this.get("model").id).find("input,select,radio,checkbox,textarea").attr("disabled","disabled"),this.$(".J-button-area,.input-group-addon").addClass("hidden")):(this.$("#"+this.get("model").id).find("input,select,radio,checkbox,textarea").removeAttr("disabled"),this.$(".J-button-area,.input-group-addon").removeClass("hidden"))},destroy:function(a){for(var b=this.get("model")||{},c=a||b.items||[],d=0;d<c.length;d++){var e=c[d].type||"text";"function"==typeof o[e].destroy&&o[e].destroy(c[d],b)}},hide:function(a){a.constructor===String&&(a=[a]);for(var b=0;b<a.length;b++){var c=this.getItemConfig(a[b]);this.$("."+c.fid).parents(".form-group").addClass("hidden")}return this},show:function(a){a.constructor===String&&(a=[a]);for(var b=0;b<a.length;b++){var c=this.getItemConfig(a[b]);this.$("."+c.fid).parents(".form-group").removeClass("hidden")}return this},trigger:function(a,b){var c=this.getItemConfig(a);"select"==c.type?this.$("select."+c.fid).trigger(b):this.$("."+c.fid).trigger(b)},valid:function(){return this.$("form").valid()}});c.exports=p}),define("eling/component/ui/form/2.0.0/dist/formutils",["gallery/handlebars/2.0.0/handlebars-seajs"],function(a,b,c){var d=a("gallery/handlebars/2.0.0/handlebars-seajs"),e={_getValue:function(a,b){if(a&&"object"==typeof b)for(var c=a.split("."),d=0;d<c.length;d++)0===b[c[d]]?b[c[d]]="0":(b[c[d]]===!0||b[c[d]]===!1)&&(b[c[d]]=b[c[d]]+""),b=b[c[d]]?b[c[d]]:"";return b?b:0===b?"0":b===!0||b===!1?b+"":""},geneValidate:function(a){for(var b="",c=a||[],d=0;d<c.length;d++)b+="data-rule-"+c[d]+"=true ";return b},compile:function(a,b){return d.compile(a)(b)}};c.exports=e}),define("eling/component/ui/form/2.0.0/dist/formvalidate",[],function(a,b,c){jQuery.validator.addMethod("alphanumeric",function(a,b){return this.optional(b)||/^\w+$/i.test(a)},"只允许输入字母、数字、下划线"),jQuery.validator.addMethod("money",function(a,b){return this.optional(b)||/(^(([0-9]|([1-9][0-9]{0,9}))((\.[0-9]{1,2})?))$)/i.test(a)},"只能输入数字"),jQuery.validator.addMethod("lettersonly",function(a,b){return!0},"只允许输入字母"),jQuery.validator.addMethod("nowhitespace",function(a,b){return this.optional(b)||/^\S+$/i.test(a)},"请不要输入空格"),jQuery.validator.addMethod("number",function(a,b){return this.optional(b)||/(^[1-9]{1}[0-9]{0,6}$)/i.test(a)},"请输入1-9999999之间的有效数字"),jQuery.validator.addMethod("floor",function(a,b){return this.optional(b)||/(^[1-9]{1}[0-9]{0,1}$)/i.test(a)},"请输入1-99之间的有效数字"),jQuery.validator.addMethod("area",function(a,b){return this.optional(b)||/(^[1-9]{1}[0-9]{0,2}$)/i.test(a)},"请输入1-999之间的有效数字"),jQuery.validator.addMethod("stature",function(a,b){return this.optional(b)||/(^[1-3]{1}[0-9]{2}$)/i.test(a)},"请输入1-300之间的有效数字"),jQuery.validator.addMethod("weight",function(a,b){var c=jQuery(b).val();return 2==c.length&&/(^[2-9]{1}[0-9]{1}$)/i.test(c)?!0:3==c.length&&/(^[1-5]{1}[0-9]{2}$)/i.test(c)?!0:0==c.length?!0:!1},"请输入20-500之间的数字"),jQuery.validator.addMethod("waistline",function(a,b){var c=jQuery(b).val();return 2==c.length&&/(^[3-9]{1}[0-9]{1}$)/i.test(c)?!0:3==c.length&&/(^[1-2]{1}[0-9]{2}$)/i.test(c)?!0:0==c.length?!0:!1},"请输入30-200之间的有效数字"),jQuery.validator.addMethod("BMI",function(a,b){return this.optional(b)||/(^[1-5]{1}[0-9]{0,1}$)/i.test(a)},"请输入10-50之间的有效数字"),jQuery.validator.addMethod("eye",function(a,b){return this.optional(b)||/(^[4-5]{1}[.]?[0-3]?$)/i.test(a)},"请输入4.0-5.3之间的有效数字"),jQuery.validator.addMethod("phone",function(a,b){return!0},"请输入一个有效的电话号码."),jQuery.validator.addMethod("mobile",function(a,b){return!0},"请输入一个有效的移动电话号码"),jQuery.validator.addMethod("postalcode",function(a,b){return this.optional(b)||/^[1-9]\d{5}$/.test(a)},"请输入6位有效数字，具体请查询中国邮政编码"),jQuery.validator.addMethod("physicaldata",function(a,b){return 0==a.length?!0:this.optional(b)||/(^[-]?[0-9]{1,2}\.?[0-9]{0,13}?$)/i.test(a)},"请输入15位以内的有效数字"),jQuery.validator.addMethod("comma_split_5",function(a,b,c){for(var d=!0,e=!0,f=jQuery(b).val().split("，"),g=0;g<f.length;g++){for(var h=f[g],i=0;i<h.length;i++)if("、"==h[i]){text2=h.split("、");for(var j=0;j<text2.length;j++){if(text2[j].length>5){d=!1;break}j==text2.length-1&&(e=!1)}}if(0!=e){if(f[g].length>5){d=!1;break}}else e=!0}return d},jQuery.validator.format("请将食物之间用中文逗号或顿号隔开，并且每个食物名称不能超过5个字"));var d="";jQuery.validator.addMethod("healthData",function(a,b,c){var e=jQuery(b).parents(".form-group").prev().prev().find("option:selected").attr("value");if(d="","BloodPressureV1"==e){d="请按照 高压值(0-250)/低压值(0-250) 的格式输入血压";var f=jQuery(b).val().split("/");if(f.length<2)return!1;for(var g=!0,h=0;h<f.length;h++){var i=f[h];if(2==i.length&&/(^[0-9]{1}[0-9]{1}$)/i.test(i))g=!0;else{if(3!=i.length||!/(^[1-2]{1}[0-9]{2}$)/i.test(i)){g=!1;break}/(^[2]{1}[6-9]{2}$)/i.test(i)&&(g=!1)}}return g}if("BGDataV1"==e)return d="请输入1-7.9之间的有效数字",this.optional(b)||/(^[1-7][.]?[0-9]?$)/i.test(a);if("FatDataV1"==e)return d="请输入1-49.9之间的有效数字",this.optional(b)||/(^[1-4]{1}[0-9]{0,1}[.]?[0-9]?$)/i.test(a);if("PulseData"==e){d="请输入30-200之间的有效数字";var f=jQuery(b).val();return 2==f.length&&/(^[3-9]{1}[0-9]{1}$)/i.test(f)?!0:3==f.length&&/(^[1-2]{1}[0-9]{2}$)/i.test(f)?!0:0==f.length?!0:!1}return"Spo2DataV1"==e?(d="请输入1-99.9之间的有效数字",this.optional(b)||/(^[1-9]{1}[0-9]{0,1}[.]?[0-9]?$)/i.test(a)):"TemperatureData"==e?(d="请输入30-49.9之间的有效数字",this.optional(b)||/(^[3-4]{1}[0-9]{0,1}[.]?[0-9]?$)/i.test(a)):"StepDataV1"==e?(d="请输入1-99999之间的有效数字",this.optional(b)||/(^[1-9]{1}[0-9]{0,4}$)/i.test(a)):!0},function(){return d}),jQuery.validator.addMethod("number_split",function(a,b,c){for(var d=jQuery(b).val().split(","),e=!0,f=0;f<d.length;f++){var g=d[f];if(1==g.length&&/(^[0-9]{0,1}$)/i.test(g))e=!0;else{if(2!=g.length||!/(^[1]{1}[0-9]{0,1}$)/i.test(g)){if(g.length>2){e=!1;break}if(0==g.length){e=!1;break}e=!1;break}e=!0}}return e},jQuery.validator.format("请使用英文逗号分割楼层，且楼层是1~19之间的数字")),jQuery.validator.addMethod("zeronumber",function(a,b){return this.optional(b)||/(^[0-9]{1}[0-9]{0,1}$)/i.test(a)},"请输入0-99之间的有效数字"),jQuery.validator.addMethod("positivenumber",function(a,b){return!isNaN(a)&&parseFloat(a)>=0},"请输入一个正浮点数")}),define("eling/component/ui/form/2.0.0/dist/form.css",[],function(){seajs.importStyle(".el-form .required{color:red;line-height:30px;font-size:20px;float:left;margin-top:3px;margin-right:10px}.el-form .form-group{min-height:34px}.el-form .el-form-buttons{background-color:#f4f4f4;padding:20px;border-top:1px solid #e5e5e5;margin-top:20px}.el-form .clear{clear:both}@media (min-width:768px){.el-form .el-form-buttons{padding:20px 0}}")}),define("eling/component/ui/form/2.0.0/dist/plugins/date",["jquery/jquery-plugins/datetimepicker/jquery.datetimepicker","eling/component/ui/form/2.0.0/dist/formutils","gallery/handlebars/2.0.0/handlebars-seajs"],function(a,b,c){a("jquery/jquery-plugins/datetimepicker/jquery.datetimepicker");var d=a("eling/component/ui/form/2.0.0/dist/formutils"),e=a("eling/component/ui/form/2.0.0/dist/plugins/date.tpl"),f={_transMode:function(a){return a?a.replace("Y","YYYY").replace("m","MM").replace("d","DD").replace("H","HH").replace("i","mm"):"YYYY-MM-DD"},_getDefaultValue:function(a){var b=a.defaultValue||"";return b?moment(b).valueOf():""},getTemplate:function(a){var b=this._getDefaultValue(a);return d.compile(e,{name:a.name,fid:a.fid,value:b?moment(b).format(this._transMode(a.mode)):"",placeholder:a.placeholder||"请输入"+a.label,validate:d.geneValidate(a.validate),style:a.style,className:a.className})},initPlugin:function(a,b,c){var d=c.mode||"Y-m-d";a.find("."+c.fid).datetimepicker({format:d,closeOnDateSelect:!0,datepicker:-1!=d.indexOf("Y")?!0:!1,timepicker:-1!=d.indexOf("H")?!0:!1,lang:"zh",scrollInput:!1,yearStart:"1900",scrollMonth:!1,step:c.step||60})},getText:function(a,b){return b.find("."+a.fid).val()},getValue:function(a,b){var c=b.find("."+a.fid).val();return c?moment(c).valueOf():""},setValue:function(a,b,c){a=d._getValue(b.value,a)||a||"","date"==typeof a?a=a.getTime():"object"==typeof a?a=a.valueOf():a&&(a=Number(a));var e=this._transMode(b.mode);-1325491557e3>a&&(a+=357e3),c.find("."+b.fid).val(a?moment(a).format(e):"")},reset:function(a,b){var c=this._getDefaultValue(a);c?b.find("."+a.fid).val(moment(c).format(this._transMode(a.mode))):b.find("."+a.fid).val("")},destroy:function(a,b){$("."+a.fid).datetimepicker("destroy")}};c.exports=f}),define("eling/component/ui/form/2.0.0/dist/plugins/date.tpl",[],'<div class="datetimepicker input-group" style=\'width:100%;\'>\n	<input name="{{this.name}}" class="form-control {{this.fid}} {{this.className.value}}" value="{{this.value}}" \n		type="text" placeholder="{{this.placeholder}}" {{this.validate}} style="{{this.style.value}}"/>\n</div>'),define("eling/component/ui/form/2.0.0/dist/plugins/place",["jquery/jquery-plugins/select2/select2","eling/component/utils/ajaxwrapper","dialog","store"],function(a,b,c){a("jquery/jquery-plugins/select2/select2");var d=a("eling/component/utils/ajaxwrapper"),e={getTemplate:function(a){var b="";return b+="<select style='display:inline-block;width:20%;' class='J-province form-control'></select>",b+="&nbsp;省&nbsp;",b+="<select style='display:inline-block;width:20%;' class='J-city form-control'></select>",b+="&nbsp;市&nbsp;",b+="<select style='display:inline-block;width:20%;' class='J-place form-control'></select>",b+="&nbsp;县&nbsp;",b+="<input id='"+a.id+"' name='"+a.name+"' class='J-"+a.name+" J-hidden-pkPlace' type='hidden'/>"},events:function(){return{"change .J-province":function(a){var b=$(a.target).parent("div"),c=$(a.target),e=b.find("select.J-city"),f=b.find("select.J-place"),g=b.find(".J-hidden-pkPlace"),h=c.find("option:selected").attr("id");g.val(h);var i=c.find("option:selected").attr("value");i?d.ajax({url:"api/admindivision",data:{parentCode:i},dataType:"json",success:function(a){var b="";b+="<option value=''>请选择</option>";for(var c=0;c<a.length;c++)b+="<option id='"+a[c].id+"'value='"+a[c].code+"'>"+a[c].name+"</option>";e.html(b),e.select2({}),f.html("<option value=''>请选择</option>"),f.select2("val",[""])}}):(e.html("<option value=''>请选择</option>"),f.html("<option value=''>请选择</option>"),e.select2({}),f.select2({}))},"change .J-city":function(a){var b=$(a.target).parent("div"),c=b.find("select.J-city"),e=b.find("select.J-place"),f=b.find(".J-hidden-pkPlace"),g=c.find("option:selected").attr("id");f.val(g);var h=c.find("option:selected").attr("value");h?d.ajax({url:"api/admindivision",data:{parentCode:h},dataType:"json",success:function(a){var b="";b+="<option value=''>请选择</option>";for(var c=0;c<a.length;c++)b+="<option id='"+a[c].id+"'value='"+a[c].code+"'>"+a[c].name+"</option>";e.html(b),e.select2({})}}):(e.html("<option value=''>请选择</option>"),e.select2({}))},"change .J-place":function(a){var b=$(a.target).parent("div"),c=b.find("select.J-place"),d=b.find(".J-hidden-pkPlace"),e=c.find("option:selected").attr("id");d.val(e)}}},setValue:function(a,b,c){var e=a?a.id:"",f=c.find(".J-hidden-pkPlace");f.val(e);var g=a?a.code:"",h=g.substring(0,2),i=f.parent("div");i.find(".J-province").select2("val",[h]);var j=g.length>=4?g.substring(0,4):"",k=i.find("select.J-city");d.ajax({url:"api/admindivision",data:{parentCode:h},dataType:"json",success:function(a){var b="";b+="<option value=''>请选择</option>";for(var c=0;c<a.length;c++)b+="<option id='"+a[c].id+"' value='"+a[c].code+"'>"+a[c].name+"</option>";k.html(b),k.select2({}),k.select2("val",[j])}});var l=i.find("select.J-place"),m=6==g.length?g:"";4==j.length?d.ajax({url:"api/admindivision",data:{parentCode:j},dataType:"json",success:function(a){var b="";b+="<option value=''>请选择</option>";for(var c=0;c<a.length;c++)b+="<option id='"+a[c].id+"' value='"+a[c].code+"'>"+a[c].name+"</option>";l.html(b),l.select2({}),l.select2("val",[m])}}):4==j.length?l.select2("val",[m]):l.select2("val",[""])},getValue:function(a,b){return b.find("."+a.fid).val()},initPlugin:function(a,b,c){d.ajax({url:"api/admindivision",dataType:"json",success:function(b){var c="";c+="<option value=''>请选择</option>";for(var d=0;d<b.length;d++)c+="<option id='"+b[d].id+"' value='"+b[d].code+"'>"+b[d].name+"</option>";a.find(".J-province").html(c),a.find(".J-province").select2({}),a.find(".J-city").html("<option value=''>请选择</option>").select2({}),a.find(".J-place").html("<option value=''>请选择</option>").select2({})}})},reset:function(a,b){b.find(".J-province").select2("val",[""]),b.find(".J-city").select2("val",[""]),b.find(".J-place").select2("val",[""]),b.find(".J-hidden-pkPlace."+a.fid).val("")}};c.exports=e}),define("eling/component/ui/form/2.0.0/dist/plugins/place1",["eling/component/ui/form/2.0.0/dist/formutils","gallery/handlebars/2.0.0/handlebars-seajs","eling/component/utils/ajaxwrapper","dialog","store","eling/component/ui/admindivision/1.0.0/dist/admindivision","eling/component/core/uicomponent/2.0.0/src/uicomponent","base","tools","handlebars","theme","templatable","bootstrap"],function(a,b,c){var d=a("eling/component/ui/form/2.0.0/dist/formutils"),e=a("eling/component/ui/form/2.0.0/dist/plugins/place1.tpl"),f=(a("eling/component/utils/ajaxwrapper"),a("eling/component/ui/admindivision/1.0.0/dist/admindivision")),g={getTemplate:function(a){return d.compile(e,{name:a.name,fid:a.fid,placeholder:a.placeholder||"请输入"+a.label,validate:d.geneValidate(a.validate),style:a.style,className:a.className})},events:function(a,b){var c=this,d={};return d["click ."+a.fid+"-value"]=function(d){a.itemSelected=function(d,e){c.setValue(d,a,b.element),"function"==typeof a.onItemSelect&&a.onItemSelect(d,e)},a.reset=function(){c.reset(),"function"==typeof a.onReset&&a.onReset()},a.close=function(){"function"==typeof a.onClose&&a.onClose()};new f({parentNode:b.element.find("."+a.fid+"-admindivision"),model:a});$("."+a.fid+"-admindivision").removeClass("hidden")},d},setValue:function(a,b,c){c.find("."+b.fid).val(a.id),c.find("."+b.fid+"-value").val(a.fullName)},getValue:function(a,b){return b.find("."+a.fid).val()},initPlugin:function(a,b,c){},reset:function(a,b){b.find("."+a.fid).val(""),b.find("."+a.fid+"-value").val("")}};c.exports=g}),define("eling/component/ui/form/2.0.0/dist/plugins/place1.tpl",[],'<input  class="form-control {{this.fid}}-value {{this.className.value}}" \n	placeholder="{{this.placeholder}}" {{this.validate}} readonly="readonly" style="cursor:pointer;{{this.style.value}}"/>\n<input name="{{this.name}}" type="hidden" class="{{this.fid}}"/>\n<div class="hidden {{this.fid}}-admindivision"></div>\n'),define("eling/component/ui/form/2.0.0/dist/plugins/select",["jquery/jquery-plugins/select2/select2","eling/component/utils/ajaxwrapper","dialog","store","eling/component/ui/form/2.0.0/dist/formutils","gallery/handlebars/2.0.0/handlebars-seajs"],function(a,b,c){a("jquery/jquery-plugins/select2/select2");var d=a("eling/component/utils/ajaxwrapper"),e=a("eling/component/ui/form/2.0.0/dist/formutils"),f=a("eling/component/ui/form/2.0.0/dist/plugins/select.tpl"),g={},h={_geneOptions:function(a,b){var c="",d=b.key||"key",f=b.value||"value",g=b.valueFormat;if(!b.multi){var h=b.emptyText||"请选择";c+="<option value=''>"+h+"</option>"}for(var i=0;i<a.length;i++){for(var j=f.split(","),k="",l=0;l<j.length;l++)k+=e._getValue(j[l],a[i])+" ";"function"==typeof g&&(k=g(k)),c+="<option value='"+e._getValue(d,a[i])+"'>"+k+"</option>"}return c},getTemplate:function(a){return e.compile(f,{name:a.name,fid:a.fid,validate:e.geneValidate(a.validate),multi:a.multi?"multiple":"",style:a.style,className:a.className})},getValue:function(a,b){return b.find("."+a.fid).select2("val")},setValue:function(a,b,c){var d=b.key||"key";a&&"object"==typeof a&&a.constructor==Array||(a=a?[a]:[]);for(var f=[],g=0;g<a.length;g++)"object"==typeof a[g]?f.push(e._getValue(d,a[g])):f.push(a[g]);0!=f.length?c.find("select."+b.fid).select2("val",f):c.find("select."+b.fid).select2("val",b.defaultValue||"")},reset:function(a,b){this.setAttribute(a,"readonly",!1),this.load({options:g[a.name]||[]},a,b)},initPlugin:function(a,b,c){var e=this,f=a.find("select."+c.fid);if(c.url&&c.lazy!==!0){var h=c.params;"function"==typeof h&&(h=h()),d.ajax({url:c.url,dataType:"json",data:h,success:function(a){g[c.name]=a,f.html(e._geneOptions(a,c)),f.select2({}),f.select2("val",c.defaultValue||"")}})}else c.options?(g[c.name]=c.options,f.html(this._geneOptions(c.options,c)),f.select2({}),f.select2("val",c.defaultValue||"")):(f.select2({}),f.select2("val",c.defaultValue||""))},load:function(a,b,c){var e=this,f=c.find("select."+b.fid);if(a&&a.options)a.notCache||(g[b.name]=a.options),f.html(e._geneOptions(a.options,b)),f.select2({}),f.select2("val",b.defaultValue||""),a.callback&&a.callback(a.options);else{var h=a&&a.params?a.params:b.params;"function"==typeof h&&(h=h()),d.ajax({url:b.url,dataType:"json",data:h,success:function(c){a&&a.notCache||(g[b.name]=c),f.html(e._geneOptions(c,b)),f.select2({}),f.select2("val",b.defaultValue||""),a&&a.callback&&a.callback(c)}})}},setData:function(a,b,c){this.load({options:a,notCache:!0},b,c)},getData:function(a,b,c){var d=a?a.pk:"",f=(g[b.name]||[]).slice();if(!d)return f||[];if(d.constructor==Array){for(var h=[],i=0;i<d.length;i++){var j=a;j.pk=d[i],h.push(this.getData(j,b,c))}return h}for(var k=b.key,l=f||[],m=0;m<l.length;m++)if(e._getValue(k,l[m])==a.pk)return a.field?e._getValue(a.field,l[m]):l[m]},setAttribute:function(a,b,c){"readonly"==b&&$("."+a.fid).select2(b,c)},removeAttribute:function(a,b){"readonly"==b?$("."+a.fid).select2("readonly",!1):$("."+a.fid).removeAttr(b)}};c.exports=h}),define("eling/component/ui/form/2.0.0/dist/plugins/select.tpl",[],'<select name="{{this.name}}" class="form-control {{this.fid}} {{this.className.value}}" {{this.validate}} {{this.multi}} style="{{this.style.value}}"></select>'),define("eling/component/ui/form/2.0.0/dist/plugins/text",["eling/component/ui/form/2.0.0/dist/formutils","gallery/handlebars/2.0.0/handlebars-seajs"],function(a,b,c){var d=a("eling/component/ui/form/2.0.0/dist/formutils"),e=a("eling/component/ui/form/2.0.0/dist/plugins/text.tpl"),f={_getDefaultValue:function(a){return a.defaultValue||(0==a.defaultValue?0:"")},getTemplate:function(a){return d.compile(e,{name:a.name,fid:a.fid,value:this._getDefaultValue(a),type:a.type,placeholder:a.placeholder||"请输入"+a.label,validate:d.geneValidate(a.validate),readonly:a.readonly?"readonly=readonly":"",style:a.style,className:a.className})},getValue:function(a,b){return b.find("."+a.fid).val()},setValue:function(a,b,c){c.find("."+b.fid).val(d._getValue(b.value||"key",a)||a||this._getDefaultValue(b)||"")},reset:function(a,b){b.find("."+a.fid).val(this._getDefaultValue(a))}};c.exports=f}),define("eling/component/ui/form/2.0.0/dist/plugins/text.tpl",[],'<input name="{{this.name}}" class="form-control {{this.fid}} {{this.className.value}}" value="{{this.value}}" \n	type="{{this.type}}" placeholder="{{this.placeholder}}" {{this.validate}} {{this.readonly}} style="{{this.style.value}}"/>\n'),define("eling/component/ui/form/2.0.0/dist/plugins/textarea",["eling/component/ui/form/2.0.0/dist/formutils","gallery/handlebars/2.0.0/handlebars-seajs"],function(a,b,c){var d=a("eling/component/ui/form/2.0.0/dist/formutils"),e=a("eling/component/ui/form/2.0.0/dist/plugins/textarea.tpl"),f={_getDefaultValue:function(a){return a.defaultValue||(0==a.defaultValue?0:"")},getTemplate:function(a){return d.compile(e,{name:a.name,fid:a.fid,value:this._getDefaultValue(a),placeholder:a.placeholder||"请输入"+a.label,validate:d.geneValidate(a.validate),readonly:a.readonly?"readonly=readonly":"",style:a.style,className:a.className})},initPlugin:function(a,b,c){var d=a.find("."+c.fid);d.on("propertychange",function(a){var b=this.scrollHeight<50?50:this.scrollHeight;this.style.height=b+"px"}),d.on("input",function(a){var b=this.scrollHeight<50?50:this.scrollHeight;this.style.height=b+"px"})},getValue:function(a,b){return b.find("."+a.fid).val()},setValue:function(a,b,c){var e=c.find("."+b.fid);e.val(d._getValue(b.value,a)||a||this._getDefaultValue(b)||""),c.removeClass("hidden"),c.parent().removeClass("hidden"),e.trigger("input")},reset:function(a,b){b.find("."+a.fid).val(this._getDefaultValue(a))}};c.exports=f}),define("eling/component/ui/form/2.0.0/dist/plugins/textarea.tpl",[],'<textarea name="{{this.name}}" class="form-control {{this.fid}} {{this.className.value}}"\n placeholder="{{this.placeholder}}" {{this.validate}} {{this.readonly}} style="{{this.style.value}}">{{this.value}}</textarea>\n'),define("eling/component/ui/form/2.0.0/dist/plugins/checkradio",["eling/component/ui/form/2.0.0/dist/formutils","gallery/handlebars/2.0.0/handlebars-seajs"],function(a,b,c){var d=a("eling/component/ui/form/2.0.0/dist/formutils"),e=a("eling/component/ui/form/2.0.0/dist/plugins/checkradio.tpl"),f={_getBooleanValue:function(a){return"true"===a?!0:"false"===a?!1:a},getTemplate:function(a){for(var b=[],c=a.list||[],f=0;f<c.length;f++){var g={name:c[f].name||a.name,fid:a.fid,type:c[f].type||("checklist"==a.type?"checkbox":"radio"),key:c[f].key,value:c[f].value,isDefault:c[f].isDefault?"checked='checked'":"",disabled:c[f].disabled?"disabled='disabled'":"",validate:d.geneValidate(a.validate),style:a.style,className:a.className};b.push(g)}return d.compile(e,b)},getValue:function(a,b){var c=this,d=a.fid,e=a.list||[],f=[];return b.find("."+d).each(function(a,b){b.checked&&f.push(c._getBooleanValue(e[a].key))}),"checklist"==a.type?f:f[0]},setValue:function(a,b,c){if(!a)return void this.reset(b,c);c.find("."+b.fid).removeAttr("checked"),a="checklist"==b.type?a:[a];for(var e=0;e<a.length;e++){var f=d._getValue(b.value||"key",a[e])||a[e]||"";c.find("."+b.fid+"-"+f).prop("checked","checked")}},reset:function(a,b){b.find("."+a.fid).removeAttr("checked");for(var c=a.list||[],d=0;d<c.length;d++)c[d].isDefault&&b.find("."+a.fid+"-"+c[d].key).prop("checked","checked")}};c.exports=f}),define("eling/component/ui/form/2.0.0/dist/plugins/checkradio.tpl",[],'<div>\n	{{#each this}}\n	<label class="{{this.type}}-inline">\n		<input name="{{this.name}}" value="{{this.key}}" type="{{this.type}}" {{this.validate}} \n			class="{{this.fid}} {{this.fid}}-{{this.key}}" {{this.isDefault}} {{this.disabled}}>{{this.value}}\n	</label>\n	{{/each}}\n</div>\n'),define("eling/component/ui/form/2.0.0/dist/plugins/autocomplete",["jquery/jquery-plugins/autocomplete/autocomplete","eling/component/ui/form/2.0.0/dist/formutils","gallery/handlebars/2.0.0/handlebars-seajs"],function(a,b,c){a("jquery/jquery-plugins/autocomplete/autocomplete");var d=a("eling/component/ui/form/2.0.0/dist/formutils"),e={getTemplate:function(a){var b=d.geneValidate(a.validate);return"<input type='hidden' name='"+a.name+"' class='J-"+a.name+"'/><input class='form-control J-autocomplete-"+a.name+"' placeholder='请输入关键字' "+b+"/>"},initPlugin:function(a,b,c){var e=c.params;"function"==typeof e&&(e=e()),a.find(".J-autocomplete-"+c.name).autocomplete({url:c.url,minChars:1,extraParams:e,processData:function(a,b){for(var e=[],f=0;f<a.length;f++)e.push({value:d._getValue(c.value||"value",a[f]),data:a[f]});return e},showResult:function(a,b){return c.showResult?c.showResult(a,b):a},onItemSelect:function(a){var b=a.data,e=c.key||"keyField",f=c.value||"valueField";$(".J-"+c.name).val(b[e]);var g=d._getValue(f,b);$(".J-autocomplete-"+c.name).val(c.showResult?c.showResult(g,b):g)},mustMatch:!0,maxItemsToShow:5,selectFirst:!1,autoFill:!1,selectOnly:!0,remoteDataType:"json"})},getValue:function(a,b){},setValue:function(a,b,c,d){var e=b.key||"key",f=a[e]||"",g=b.showResult?b.showResult(a,d):a;c.find(".J-"+b.name).val(f),c.find(".J-autocomplete-"+b.name).val(g)},reset:function(a,b){}};c.exports=e}),define("eling/component/ui/form/2.0.0/dist/form.tpl",[],"<div class='container el-form'>\n	<div class='row'>\n		<div class='col-xs-12'>\n			<div class='row'>\n				<div class='col-sm-12'>\n					<div class='box'>\n						<div class='box-content'>\n							<form class=\"form form-horizontal\" id=\"{{this.id}}\">\n								{{#each this.items}}\n									<div class=\"form-group {{this.show}} {{this.className.container}}\" style=\"{{this.style.container}}\">\n										<label class=\"col-md-2 control-label J-form-label-{{this.name}} \n											{{this.className.label}}\" style=\"{{this.style.label}}\">{{this.label}}</label>\n										<div class=\"col-md-6 {{this.className.valueContainer}}\">\n											{{#form_2_0_0 this}}{{/form_2_0_0}}\n										</div>\n										{{#if this.isRequired}}\n										<div class=\"required\">*</div>\n										{{/if}}\n									</div>\n								{{/each}}\n								<div class=\"clear\"></div>\n								{{#if this.defaultButton}}\n								<div class='el-form-buttons J-button-area'>\n									<div class='row'>\n										<div class='col-md-10 col-md-offset-2'>\n											<button class='J-form-save J-form-{{this.id}}-save btn' type='submit' style=\"color:white;background: {{this.theme}};\">\n												<span class='J-form-saveText J-form-{{this.id}}-saveText'>{{this.saveText}}</span>\n											</button>\n	                            			<a class='J-form-cancel J-form-{{this.id}}-cancel btn' href=\"javascript:void(0);\">{{this.cancelText}}</a>\n	                          			</div>\n	                       			</div>\n                      			</div>\n                      			{{/if}}\n                      		</form>\n                      	</div>\n                    </div>\n                </div>\n            </div>\n        </div>\n    </div>\n</div>\n                      	");