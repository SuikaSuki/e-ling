define("eling/component/ui/file/1.0.0/dist/file",["eling/component/core/uicomponent/2.0.0/src/uicomponent","base","tools","handlebars","templatable","bootstrap","eling/component/utils/dialog/2.0.0/dist/dialog","gallery/handlebars/2.0.0/handlebars-seajs","jquery/jquery-plugins/toastr/toastr.min","eling/component/utils/tools/tools","./file_1.tpl","./file_2.tpl","eling/component/utils/loadimgage/loadimage-debug","jquery/jquery-plugins/ajaxfileupload/ajaxfileupload","./file.css"],function(a,b,c){var d=a("eling/component/core/uicomponent/2.0.0/src/uicomponent"),e=a("eling/component/utils/dialog/2.0.0/dist/dialog"),f=a("eling/component/utils/tools/tools"),g=a("./file_1.tpl"),h=a("./file_2.tpl");a("eling/component/utils/loadimgage/loadimage-debug"),a("jquery/jquery-plugins/ajaxfileupload/ajaxfileupload"),a("./file.css");var i=d.extend({attrs:{autoRender:!0,model:{placeholder:"请选择文件",style:"cursor:pointer;",description:{text:"",style:""},button:{text:"上传",style:""},preview:{style:""},multiple:"multiple",pixel:[],maxSize:2,accept:"*",isPreview:!0}},initCustAttr:function(){var a=this.get("model");void 0===a.styleType?this.set("template",g):"2"===a.styleType&&this.set("template",h)},events:{"click .J-file-btn":function(a){var b=this.$("input[type=file]");return b.replaceWith(b=b.clone(!0)),b.click(),a.preventDefault(),!1},"change input[type='file']":function(a){var b=[],c=this.get("model");this.$(".J-preview").empty();for(var d=a.target.files,f="",g=0;g<d.length;g++)if(d[g].size>1024*c.maxSize*1024)b.push(d[g].name+"的大小超过了"+c.maxSize+"M");else{var h=d[g].name.substring(d[g].name.lastIndexOf(".")+1);if("*"===c.accept||-1!=c.accept.indexOf(h)){if(f+=d[g].name+"；",c.isPreview!==!1){var i=window.loadImage(d[g]);$(i).addClass("preview-item").attr("style",c.preview.style),this.$(".J-preview").append(i)}}else b.push(d[g].name+"的格式不正确。要求的格式为："+c.accept)}if(this.$("input[type='text']").val(f.substring(0,f.length-1)),0!=b.length){var j="";for(var g in b)j+=b[g]+"\n";e.alert({id:"el-dialog-validate-fail-tip",title:"提示",content:j,confirm:function(){return e.close("el-dialog-validate-fail-tip"),!1}})}}},upload:function(a,b){var c=this.$("input[type='file']").attr("id"),d=this.$("input[type='file']").val();d&&$.ajaxFileUpload({url:a,secureuri:!1,fileElementId:c,dataType:"json",success:function(a){"function"==typeof b&&b(a)}})},download:function(a){this.$(".J-preview").empty(),f.isString(a)&&(a=[a]);for(var b in a){var c=$("<img>").addClass("preview-item").attr("style",this.get("model").preview.style);c.attr("src",a[b]),this.$(".J-preview").append(c)}},reset:function(){this.$(".J-preview").empty(),this.$("input[type='text']").val(""),this.$("input[type='file']").val(""),this.setDisabled(!1)},setDisabled:function(a){a?(this.$("input").attr("disabled","disabled"),this.$(".tip").addClass("hidden")):(this.$(".tip").removeClass("hidden"),this.$("input").removeAttr("disabled"))},setReadonly:function(a){this.setDisabled(a)},getValue:function(){},setValue:function(){},getData:function(){},setData:function(){}});c.exports=i}),define("eling/component/ui/file/1.0.0/dist/file_1.tpl",[],'<div class="el-file el-file-tpl-1">\n	<div class="input-group">\n		<input class="form-control" type="text" readonly="readonly" style="{{this.style}}" placeholder="{{this.placeholder}}"/>\n		<button style="{{this.button.style}}" class="btn btn-danger J-file-btn">{{this.button.text}}</button>\n		<div style="{{this.description.style}}">{{{this.description.text}}}</div>\n		<input id="{{this.id}}" name="file" type="file" {{this.multiple}} class="hidden"/>\n	</div>\n	<div class="J-preview preview"></div>\n</div>'),define("eling/component/ui/file/1.0.0/dist/file_2.tpl",[],'<div class="el-file el-file-tpl-2">\n    <div class="J-preview preview"></div>\n    <div class="tip">\n        <div class="description" style="{{this.description.style}}">{{{this.description.text}}}</div>\n        <button style="{{this.button.style}}" class="btn btn-danger J-file-btn">{{this.button.text}}</button>\n    </div>\n\n	<input id="{{this.id}}" name="file" type="file" {{this.multiple}} class="hidden"/>\n</div>'),define("eling/component/ui/file/1.0.0/dist/file.css",[],function(){seajs.importStyle(".el-file-tpl-1 .input-group{width:100%}.el-file-tpl-1 .form-control{width:70%}.el-file button{width:80px;border-radius:5px!important}.el-file-tpl-1 button{margin-left:10px;margin-top:-4px}.el-file-tpl-2 button{bottom:0;position:absolute}.el-file-tpl-2 .description{padding-top:20px}.el-file-tpl-2.el-file .tip,.el-file-tpl-2.el-file .preview{display:inline-block;vertical-align:top}.el-file-tpl-2 .tip{position:relative}.el-file-tpl-1 .preview-item,.el-file-tpl-2 .preview-item{margin:5px 10px;cursor:pointer;width:100px;height:100px}")});