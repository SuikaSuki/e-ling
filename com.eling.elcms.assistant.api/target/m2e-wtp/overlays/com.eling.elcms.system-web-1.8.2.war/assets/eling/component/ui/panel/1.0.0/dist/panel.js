define("eling/component/ui/panel/1.0.0/dist/panel",["eling/component/core/uicomponent/2.0.0/src/uicomponent","base","tools","handlebars","theme","templatable","bootstrap","eling/component/utils/ajaxwrapper","dialog","store","gallery/handlebars/2.0.0/handlebars-seajs","./panel.css","eling/component/utils/tools/tools","./panel.tpl"],function(a,b,c){var d=a("eling/component/core/uicomponent/2.0.0/src/uicomponent"),e=a("eling/component/utils/ajaxwrapper");a("gallery/handlebars/2.0.0/handlebars-seajs");a("./panel.css");var f=a("eling/component/utils/tools/tools"),g=d.extend({attrs:{template:a("./panel.tpl"),autoRender:!0,model:{col:4}},_loadData:function(a,b){var c=this,d=b||this.get("params")||"";"function"==typeof d&&(d=d());var f=this.get("autoRender");0!=f&&(this.loading(),e.ajax({url:a,dataType:"json",type:"GET",data:d,success:function(a){c.setData(a),c.element.find(".ajaxloader").remove()},error:function(a){}}))},refresh:function(a){this.set("autoRender",!0);var b=this.get("url");this._loadData(b,a)},setup:function(){this.render();var a=this.get("url");a&&this._loadData(a)},setData:function(a){var b=this.get("model");b.data=a;for(var c=b.labelWidth,d=b.valueWidth,e=b.items||[],g=b.col,h=0;h<e.length;h++){var i=e[h].name,j=f._getValueWithPoint(i,a),k=e[h].format;k&&(j=k(j,a)),e[h].value=j,h%g==0&&0!=h&&(e[h].n=!0),e[h].labelWidth=c+"%",e[h].valueWidth=d+"%",e[h].colWidth=100/g-1+"%"}this.renderPartial(".J-panel-content"),this.loadPicture(a)},loadPicture:function(a){var b=this.get("model").img;b&&this.$("img.J-panel-img").attr("src",b.url+(a[b.idAttribute]||"0"))},loading:function(){this.element.find(".J-panel-content").html("<div class='ajaxloader'></div>"),this.get("model").data=[]}});c.exports=g}),define("eling/component/ui/panel/1.0.0/dist/panel.css",[],function(){seajs.importStyle(".el-panel .ajaxloader{background:url(assets/eling/resources/ajaxloader/ajaxloader.gif) no-repeat;height:50px;background-position:50%}.el-panel .form-control{border:0 none;box-shadow:none;-webkit-box-shadow:none;-molliza-box-shadow:none}.el-panel .el-panel-row{font-size:14px;line-height:1.42857;color:#555;margin-left:0;margin-right:0;padding:15px 0;border-bottom:1px solid #ddd;margin-bottom:0}.el-panel .el-panel-row:nth-child(even){background-color:#f4f4f4}.el-panel .item,.el-panel .item span,.el-panel .item label{display:inline-block}.el-panel .item span{text-align:left}.el-panel .item label{text-align:right}@media print{.el-panel .el-panel-row{padding:0!important;border-bottom:0 none!important}}")}),define("eling/component/ui/panel/1.0.0/dist/panel.tpl",[],'<div class=\'container el-panel\'>\n	<div class="row">\n		{{#if this.img.show}}\n		<div class=\'col-lg-2\'>\n			<div class=\'box\'>\n				<div class=\'box-content\'>\n					<img class="J-panel-img img-responsive"/>\n				</div>\n			</div>\n		</div>\n		{{/if}}\n		{{#if this.img.show}}\n			<div class=\'col-lg-10\'>\n		{{else}}\n			<div class=\'col-lg-12\'>\n		{{/if}}\n		<div class="box">\n			<div class="box-content">\n				<div class="J-panel-content">\n					<div class="el-panel-row">\n					{{#each this.items}}\n						{{#if this.n}}\n							</div>\n							<div class="el-panel-row">\n								<div style="width: {{this.colWidth}};" class="item">\n									<label style="width:{{this.labelWidth}};">{{this.label}}：</label>\n									<span style="width:{{this.valueWidth}};" class="J-panel-{{this.id}}">{{this.value}}</span>\n								</div>\n						{{else}}\n								<div style="width: {{this.colWidth}};" class="item">\n									<label style="width:{{this.labelWidth}};">{{this.label}}：</label>\n									<span style="width:{{this.valueWidth}};" class="J-panel-{{this.id}}">{{this.value}}</span>\n								</div>\n						{{/if}}\n					{{/each}}\n					</div>\n                </div>\n              </div>\n            </div>\n          </div>\n        </div>\n</div>\n                      	');