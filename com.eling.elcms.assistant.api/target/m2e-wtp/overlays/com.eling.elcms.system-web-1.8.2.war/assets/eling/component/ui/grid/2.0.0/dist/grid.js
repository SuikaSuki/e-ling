define("eling/component/ui/grid/2.0.0/dist/grid",["jquery/jquery-plugins/pagination/pagination","eling/component/core/uicomponent/2.0.0/src/uicomponent","base","tools","handlebars","theme","templatable","bootstrap","gallery/handlebars/2.0.0/handlebars-seajs","eling/component/utils/tools/tools","./grid.tpl","eling/component/utils/ajaxwrapper","dialog","store","./grid.css"],function(a,b,c){a("jquery/jquery-plugins/pagination/pagination");var d=a("eling/component/core/uicomponent/2.0.0/src/uicomponent"),e=a("gallery/handlebars/2.0.0/handlebars-seajs"),f=a("eling/component/utils/tools/tools"),g=a("./grid.tpl"),h=a("eling/component/utils/ajaxwrapper");a("./grid.css"),e.registerHelper("grid_2",function(a,b){var c=a.data,d=a.columns,e="";for(var g in c){var h=a.idAttribute;if(e+=h?"<tr class='J-"+f._getValueFromObject(h,c[g])+"'>":"<tr>",a.isCheckbox)if(f.isFunction(a.hasChecked)){var i=a.hasChecked(c[g],g);e+=i?"<td><input class='J-grid-checkbox J-grid-"+a.id+"-checkbox' type='checkbox'></td>":"<td></td>"}else e+="<td><input class='J-grid-checkbox J-grid-"+a.id+"-checkbox' type='checkbox'></td>";if(a.isRadiobox)if(f.isFunction(a.hasChecked)){var i=a.hasChecked(c[g],g);e+=i?"<td><input class='J-grid-radiobox J-grid-"+a.id+"-radiobox' type='radio' name='grid-"+a.id+"-radiobox'></td>":"<td></td>"}else e+="<td><input class='J-grid-radiobox J-grid-"+a.id+"-radiobox' type='radio' name='grid-"+a.id+"-radiobox'></td>";for(var j in d){var k=f._getValueFromObject(d[j].name,c[g]);(null===k||void 0===k)&&(k=c[g]),e+="<td class='J-grid-columns-"+d[j].id+" "+(d[j].className||" ")+"'><pre>";var l=d[j].format;"string"==typeof l&&f["gridformat_"+l]?k=f["gridformat_"+l](d[j].formatparams,k,c[g],a):"function"==typeof l&&(k=l(k,c[g])),e+=k,e+="</pre></td>"}e+="</tr>"}return e});var i=d.extend({editors:{},attrs:{template:g,autoRender:!0,model:{id:null,head:{buttons:[]},isInitPageBar:!0,items_per_page:15,columns:[],url:null,params:null,data:[],isCheckbox:!1,isRadiobox:!1},data:[]},_loadData:function(a,b,c){var d=this,e=this.get("model"),f=b||e.params||{};"function"==typeof f&&(f=f());var g=e.isInitPageBar;this.loading(),h.ajax({url:a,data:f,success:function(a){d.set("data",a),d._setData(a,g),"function"==typeof c&&c(a)},error:function(a){d._setData([],g)}})},initCustAttr:function(){var a=this.get("model");!a.url&&this.get("url")&&(a.url=this.get("url")),!a.params&&this.get("params")&&(a.params=this.get("params"));var b=a.columns,c=a.columns.length;(a.isCheckbox||a.isRadiobox)&&(c+=1),a.length=c;var d={};for(var e in b){b[e].key&&b[e].name&&(b[e].label=b[e].name,b[e].name=b[e].key);var f=b[e].name||"",g=f.replace(/\./g,"-");b[e].id=g,d[g]=b[e]}this.set("columnsConfig",d)},events:function(){var a={},b=this.get("model"),c=this,d=this.element,e=b.columns,f=!1;for(var g in e)!function(c){e[c].issort&&(f=!0);var d=e[c].formatparams;d&&d.constructor!==Array&&(d=[d]);for(var g in d)!function(c){a["click .J-grid-"+b.id+"-"+(d[c].id||d[c].key)]=function(a){var b=this.getIndex(a.target),e=this.getData(b),f=$(a.currentTarget).parents("tr"),g=d[c].handler||function(){};g(b,e,f,a)}}(g)}(g);var h=b.head.buttons;for(var i in h)!function(c){a["click .J-grid-"+b.id+"-head-"+h[c].id]=function(){h[c].handler&&h[c].handler()}}(i);return b.isCheckbox&&(a["click .J-grid-"+b.id+"-selectAll"]=function(a){$(a.target).prop("checked")?d.find("tbody input[type='checkbox']").prop("checked",!0):d.find("tbody input[type='checkbox']").prop("checked",!1)},a["click .J-grid-checkbox"]=b.clickCheckbox||function(){return!1}),b.isRadiobox&&(a["click .J-grid-radiobox"]=b.clickRadiobox||function(){return!1}),f&&(a["click .text-sort-header"]=function(a){$(".J-sort",d).children().hide(),$(".icon-sort",d).css("display","inline"),$(".J-sort",a.currentTarget).children().hide(),"up"==c.sort?(c.sort="down",$(".icon-sort-down",a.currentTarget).css("display","inline")):(c.sort="up",$(".icon-sort-up",a.currentTarget).css("display","inline")),this.updateBySort($(a.currentTarget).attr("data-name"))}),a},afterRender:function(){var a=this,b=this.get("model"),c=b.head;if(c&&this.get("_headPluginInit")!==!0){var d=c.items||[];for(var e in d){d[e].type||"text";d[e].triggerType="form",d[e].style=d[e].style||"float:right;margin-right:10px;width:180px;",function(b){f.loadModule(b.component||b.type,function(c){var d=new c({parentNode:a.$(".J-grid-head-formel"),model:b});a.editors[b.id||b.name]=d})}(d[e])}}this.set("_headPluginInit",!0)},updateBySort:function(a){var b=this.sort,c=this.get("data").sort(function(c,d){var e=f._getValueFromObject(a,c),g=f._getValueFromObject(a,d);return"up"==b?e>g?-1:1:g>e?-1:1});this.setData(c)},setup:function(){this.render();var a=this.get("autoRender"),b=this.get("model").url;b&&a&&this._loadData(b)},_setData:function(a,b){var c=this.get("model");c.data=a;var d=this.get("data"),e="共"+(c.isInitPageBar?Math.ceil(d.length/c.items_per_page)+"页，"+d.length+"条记录":d.length+"条记录");this.$(".J-grid-total-info").text(e),b?this._initPageBar(c.data.length):this._renderBody()},_initPageBar:function(a){var b=this,c=this.get("model"),d=c.data||[];this.$(".J-pagination").pagination(a,{callback:function(e,f){var g=c.items_per_page,h=e*g,i=Math.min((e+1)*g,a),j=d.slice(h,i);b._setData(j,!1)},link_to:"javascript:void(0);",items_per_page:c.items_per_page,next_text:"下一页",prev_text:"上一页",num_display_entries:10,num_edge_entries:2})},setData:function(a){this.set("data",a||[]),this._setData(a,this.get("model").isInitPageBar)},getData:function(a){return void 0===a||isNaN(a)?this.get("data"):this.get("data")[a]},save:function(a,b){if(2==arguments.length)-1==a?this.insert(0,b):this.update(a,b);else{var c=f._getValueFromObject(this.get("model").idAttribute,a),d=this.$("tr.J-"+c);0==d.length?this.insert(0,a):this.update(this.getIndex(d),a)}},insert:function(a,b){this.get("data").splice(a,0,b),this._setData(this.get("data"),this.get("model").isInitPageBar)},update:function(a,b){var c=this.get("data");a>c.length||(a%=this.get("model").items_per_page,this.$("tbody tr").eq(a).replaceWith(this._geneRow(b)),c.splice(a,1,b))},remove:function(a){var b=null;b="number"==typeof a?a:this.getIndex(a),this.get("data").splice(b,1),this._setData(this.get("data"),this.get("model").isInitPageBar)},_geneRow:function(a){var b=this.get("model");return e.compile("{{#grid_2 this}}{{/grid_2}}")({id:b.id,idAttribute:b.idAttribute,data:[a],columns:b.columns,isCheckbox:b.isCheckbox,isRadiobox:b.isRadiobox})},refresh:function(a,b){this._loadData(this.get("model").url,a,b)},getSelectedData:function(){var a=this.get("model"),b=this.get("data");if(!b)return null;var c=this,d=[];return a.isCheckbox===!0?this.$("tbody input.J-grid-checkbox:checked").each(function(){var a=c.getIndex($(this));d.push(b[a])}):a.isRadiobox===!0&&this.$("tbody input.J-grid-radiobox:checked").each(function(){var a=c.getIndex($(this));d.push(b[a])}),d},getIndex:function(a){var b=this.get("model"),c=parseInt(this.$(".J-pagination span[class='current']").text())||0,c=c>0?c-1:0;return $(a).is("tr")?this.$(a).prevAll().size()+c*b.items_per_page:this.$(a).parents("tr").prevAll().size()+c*b.items_per_page},setTitle:function(a){this.$(".J-grid-title").text(a)},getTitle:function(){return this.$(".J-grid-title").text()},loading:function(){var a="<tr><td colspan="+this.get("model").length+'><div class="loading">{{#each this}}<div class="item item-{{@index}}"></div>{{/each}}</div></td></tr>';this.$(".J-grid-table").html(e.compile(a)(new Array(8))),this.get("model").data=[]},hideButton:function(a){var b=this.get("model");a.constructor===String&&(a=[a]);for(var c in a)this.$(".J-grid-"+b.id+"-head-"+a[c]).addClass("hidden");return this},showButton:function(a){var b=this.get("model");a.constructor===String&&(a=[a]);for(var c in a)this.$(".J-grid-"+b.id+"-head-"+a[c]).removeClass("hidden");return this},getValue:function(a,b){var c=this.get("model").data;return c[a][b]},getColumnConfig:function(a){if("number"==typeof a)return this.get("model").columns[a];if("string"==typeof a)return this.get("columnsConfig")[a];var b=null;return b=$(a).is("td")?$(a).attr("class").match(new RegExp("columns-[^ ]*"))[0]:$(a).parents("td").attr("class").match(new RegExp("columns-[^ ]*"))[0],this.get("columnsConfig")[b.substring(8,b.length)]},getColumnIndex:function(a){return $(a).is("td")?$(a).prevAll().size():$(a).parents("td").prevAll().size()},_renderBody:function(){this.$(".J-grid-table").html(e.compile("{{#grid_2 this}}{{/grid_2}}")(this.get("model")))},getheaderPlugins:function(){return this.editors},destroy:function(){for(var a in this.editors)this.editors[a].destroy();i.superclass.destroy.call(this,arguments)}});c.exports=i}),define("eling/component/ui/grid/2.0.0/dist/grid.tpl",[],"<div id=\"{{this.id}}\" class='container el-grid el-grid-2'>\n	<div class='row'>\n		<div class='col-xs-12'>\n			<div class='row'>\n				<div class='col-sm-12'>\n					<div class='box' style='margin-bottom:0;'>\n						<div class='box-header'>\n							<div class='title J-grid-title'>{{this.head.title}}</div>\n							<div class='actions'>\n								{{#each this.head.buttons}}\n								<div class=\"J-grid-{{../this.id}}-head-{{this.id}} btn\" style=\"color:white;background: {{../this.theme.bgColor}}\">\n									{{this.text}}\n									<i class=\"{{this.icon}}\"></i>\n								</div>\n								{{/each}}\n							</div>\n							<div class='actions J-grid-head-formel'></div>\n						</div>\n						<div class='box-content box-no-padding'>\n							<table style='border:0 none;margin-bottom:0;' class='data-table-column-filter table table-bordered table-striped'>\n								<thead>\n    								<tr>\n    									{{#if this.isCheckbox}}\n    										<th style='width: 5%;'><input class='J-grid-{{this.id}}-selectAll' type='checkbox'></th>\n    									{{/if}}\n    									{{#if this.isRadiobox}}\n    										<th style='width: 5%;'></th>\n    									{{/if}}\n	    								{{#each this.columns}}\n	    									<th class=\"text-center {{this.className}} {{#if this.issort}}text-sort-header{{/if}}\" data-name=\"{{this.name}}\">{{this.label}}\n	    										{{#if this.issort}}\n												<div class=\"J-sort\">\n													<div class=\"icon-sort sort-show\"></div>\n    												<div class=\"icon-sort-up sort-hide\"></div>\n    												<div class=\"icon-sort-down sort-hide\"></div>\n												</div>\n												{{/if}}\n	    									</th>\n	    								{{/each}}\n    								</tr>\n  									</thead>\n  									<tbody class=\"J-grid-table\">\n	    								{{#grid_2 this}}\n	    								{{/grid_2}}\n	                				</tbody>\n               					<tfoot class=\"J-grid-footer\">\n                					<tr style=\"display: table-row !important;\">\n               							<th colspan='{{this.length}}'>\n               								<div class=\"J-grid-total-info\" style=\"float: left;margin-top: 5px;\"></div>\n               								<div class=\"J-pagination pagination\" style=\"float: right;margin: 0;\"></div>\n               							</th>\n                					</tr>\n                				</tfoot>\n                			</table>\n		                </div>\n		            </div>\n		        </div>\n		    </div>\n		</div>\n	</div>\n</div>"),define("eling/component/ui/grid/2.0.0/dist/grid.css",[],function(){seajs.importStyle(".el-grid.el-grid-2 .loading{position:relative;width:32px;height:40px;left:50%;margin-left:-32px}.el-grid.el-grid-2 .loading .item{position:absolute;background-color:#FFF;width:5px;height:12px;-moz-border-radius:5px 5px 0 0;-moz-transform:scale(0.4);-moz-animation-name:fadeG;-moz-animation-duration:1.04s;-moz-animation-iteration-count:infinite;-moz-animation-direction:normal;-webkit-border-radius:5px 5px 0 0;-webkit-transform:scale(0.4);-webkit-animation-name:fadeG;-webkit-animation-duration:1.04s;-webkit-animation-iteration-count:infinite;-webkit-animation-direction:normal;-ms-border-radius:5px 5px 0 0;-ms-transform:scale(0.4);-ms-animation-name:fadeG;-ms-animation-duration:1.04s;-ms-animation-iteration-count:infinite;-ms-animation-direction:normal;-o-border-radius:5px 5px 0 0;-o-transform:scale(0.4);-o-animation-name:fadeG;-o-animation-duration:1.04s;-o-animation-iteration-count:infinite;-o-animation-direction:normal;border-radius:5px 5px 0 0;transform:scale(0.4);animation-name:fadeG;animation-duration:1.04s;animation-iteration-count:infinite;animation-direction:normal}.el-grid.el-grid-2 .loading .item-0{left:0;top:15px;-moz-animation-delay:.39s;-moz-transform:rotate(-90deg);-webkit-animation-delay:.39s;-webkit-transform:rotate(-90deg);-ms-animation-delay:.39s;-ms-transform:rotate(-90deg);-o-animation-delay:.39s;-o-transform:rotate(-90deg);animation-delay:.39s;transform:rotate(-90deg)}.el-grid.el-grid-2 .loading .item-1{left:4px;top:5px;-moz-animation-delay:.52s;-moz-transform:rotate(-45deg);-webkit-animation-delay:.52s;-webkit-transform:rotate(-45deg);-ms-animation-delay:.52s;-ms-transform:rotate(-45deg);-o-animation-delay:.52s;-o-transform:rotate(-45deg);animation-delay:.52s;transform:rotate(-45deg)}.el-grid.el-grid-2 .loading .item-2{left:13px;top:2px;-moz-animation-delay:.65s;-moz-transform:rotate(0deg);-webkit-animation-delay:.65s;-webkit-transform:rotate(0deg);-ms-animation-delay:.65s;-ms-transform:rotate(0deg);-o-animation-delay:.65s;-o-transform:rotate(0deg);animation-delay:.65s;transform:rotate(0deg)}.el-grid.el-grid-2 .loading .item-3{right:4px;top:5px;-moz-animation-delay:.78s;-moz-transform:rotate(45deg);-webkit-animation-delay:.78s;-webkit-transform:rotate(45deg);-ms-animation-delay:.78s;-ms-transform:rotate(45deg);-o-animation-delay:.78s;-o-transform:rotate(45deg);animation-delay:.78s;transform:rotate(45deg)}.el-grid.el-grid-2 .loading .item-4{right:0;top:15px;-moz-animation-delay:.91s;-moz-transform:rotate(90deg);-webkit-animation-delay:.91s;-webkit-transform:rotate(90deg);-ms-animation-delay:.91s;-ms-transform:rotate(90deg);-o-animation-delay:.91s;-o-transform:rotate(90deg);animation-delay:.91s;transform:rotate(90deg)}.el-grid.el-grid-2 .loading .item-5{right:4px;bottom:4px;-moz-animation-delay:1.04s;-moz-transform:rotate(135deg);-webkit-animation-delay:1.04s;-webkit-transform:rotate(135deg);-ms-animation-delay:1.04s;-ms-transform:rotate(135deg);-o-animation-delay:1.04s;-o-transform:rotate(135deg);animation-delay:1.04s;transform:rotate(135deg)}.el-grid.el-grid-2 .loading .item-6{bottom:0;left:13px;-moz-animation-delay:1.17s;-moz-transform:rotate(180deg);-webkit-animation-delay:1.17s;-webkit-transform:rotate(180deg);-ms-animation-delay:1.17s;-ms-transform:rotate(180deg);-o-animation-delay:1.17s;-o-transform:rotate(180deg);animation-delay:1.17s;transform:rotate(180deg)}.el-grid.el-grid-2 .loading .item-7{left:4px;bottom:4px;-moz-animation-delay:1.3s;-moz-transform:rotate(-135deg);-webkit-animation-delay:1.3s;-webkit-transform:rotate(-135deg);-ms-animation-delay:1.3s;-ms-transform:rotate(-135deg);-o-animation-delay:1.3s;-o-transform:rotate(-135deg);animation-delay:1.3s;transform:rotate(-135deg)}@-moz-keyframes fadeG{0%{background-color:#000}100%{background-color:#FFF}}@-webkit-keyframes fadeG{0%{background-color:#000}100%{background-color:#FFF}}@-ms-keyframes fadeG{0%{background-color:#000}100%{background-color:#FFF}}@-o-keyframes fadeG{0%{background-color:#000}100%{background-color:#FFF}}@keyframes fadeG{0%{background-color:#000}100%{background-color:#FFF}}.el-grid.el-grid-2 tbody td{vertical-align:middle!important;word-break:break-word!important;position:relative}.el-grid.el-grid-2 td pre{background:inherit;border:0 none;padding:0;margin:0;word-wrap:break-word}.el-grid.el-grid-2 th{border-bottom:0!important}.text-sort-header{cursor:pointer}.J-sort{display:inline}.sort-hide{display:none}.sort-show{display:inline}@media print{.el-grid.el-grid-2 td{padding:0 2px!important}}")});