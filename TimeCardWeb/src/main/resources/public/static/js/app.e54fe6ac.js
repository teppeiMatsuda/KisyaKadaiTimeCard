(function(e){function t(t){for(var r,o,s=t[0],u=t[1],l=t[2],c=0,f=[];c<s.length;c++)o=s[c],Object.prototype.hasOwnProperty.call(a,o)&&a[o]&&f.push(a[o][0]),a[o]=0;for(r in u)Object.prototype.hasOwnProperty.call(u,r)&&(e[r]=u[r]);p&&p(t);while(f.length)f.shift()();return i.push.apply(i,l||[]),n()}function n(){for(var e,t=0;t<i.length;t++){for(var n=i[t],r=!0,o=1;o<n.length;o++){var s=n[o];0!==a[s]&&(r=!1)}r&&(i.splice(t--,1),e=u(u.s=n[0]))}return e}var r={},o={app:0},a={app:0},i=[];function s(e){return u.p+"static/js/"+({about:"about"}[e]||e)+"."+{about:"02ac16bc"}[e]+".js"}function u(t){if(r[t])return r[t].exports;var n=r[t]={i:t,l:!1,exports:{}};return e[t].call(n.exports,n,n.exports,u),n.l=!0,n.exports}u.e=function(e){var t=[],n={about:1};o[e]?t.push(o[e]):0!==o[e]&&n[e]&&t.push(o[e]=new Promise((function(t,n){for(var r="static/css/"+({about:"about"}[e]||e)+"."+{about:"526ab0aa"}[e]+".css",a=u.p+r,i=document.getElementsByTagName("link"),s=0;s<i.length;s++){var l=i[s],c=l.getAttribute("data-href")||l.getAttribute("href");if("stylesheet"===l.rel&&(c===r||c===a))return t()}var f=document.getElementsByTagName("style");for(s=0;s<f.length;s++){l=f[s],c=l.getAttribute("data-href");if(c===r||c===a)return t()}var p=document.createElement("link");p.rel="stylesheet",p.type="text/css",p.onload=t,p.onerror=function(t){var r=t&&t.target&&t.target.src||a,i=new Error("Loading CSS chunk "+e+" failed.\n("+r+")");i.code="CSS_CHUNK_LOAD_FAILED",i.request=r,delete o[e],p.parentNode.removeChild(p),n(i)},p.href=a;var d=document.getElementsByTagName("head")[0];d.appendChild(p)})).then((function(){o[e]=0})));var r=a[e];if(0!==r)if(r)t.push(r[2]);else{var i=new Promise((function(t,n){r=a[e]=[t,n]}));t.push(r[2]=i);var l,c=document.createElement("script");c.charset="utf-8",c.timeout=120,u.nc&&c.setAttribute("nonce",u.nc),c.src=s(e);var f=new Error;l=function(t){c.onerror=c.onload=null,clearTimeout(p);var n=a[e];if(0!==n){if(n){var r=t&&("load"===t.type?"missing":t.type),o=t&&t.target&&t.target.src;f.message="Loading chunk "+e+" failed.\n("+r+": "+o+")",f.name="ChunkLoadError",f.type=r,f.request=o,n[1](f)}a[e]=void 0}};var p=setTimeout((function(){l({type:"timeout",target:c})}),12e4);c.onerror=c.onload=l,document.head.appendChild(c)}return Promise.all(t)},u.m=e,u.c=r,u.d=function(e,t,n){u.o(e,t)||Object.defineProperty(e,t,{enumerable:!0,get:n})},u.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},u.t=function(e,t){if(1&t&&(e=u(e)),8&t)return e;if(4&t&&"object"===typeof e&&e&&e.__esModule)return e;var n=Object.create(null);if(u.r(n),Object.defineProperty(n,"default",{enumerable:!0,value:e}),2&t&&"string"!=typeof e)for(var r in e)u.d(n,r,function(t){return e[t]}.bind(null,r));return n},u.n=function(e){var t=e&&e.__esModule?function(){return e["default"]}:function(){return e};return u.d(t,"a",t),t},u.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},u.p="/",u.oe=function(e){throw console.error(e),e};var l=window["webpackJsonp"]=window["webpackJsonp"]||[],c=l.push.bind(l);l.push=t,l=l.slice();for(var f=0;f<l.length;f++)t(l[f]);var p=c;i.push([0,"chunk-vendors"]),n()})({0:function(e,t,n){e.exports=n("56d7")},"034f":function(e,t,n){"use strict";var r=n("85ec"),o=n.n(r);o.a},"0eec":function(e,t,n){"use strict";var r=n("73af"),o=n.n(r);o.a},"56d7":function(e,t,n){"use strict";n.r(t);n("e260"),n("e6cf"),n("cca6"),n("a79d");var r=n("2b0e"),o=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{attrs:{id:"app"}},[n("div",{attrs:{id:"nav"}}),n("router-view")],1)},a=[],i=(n("034f"),n("2877")),s={},u=Object(i["a"])(s,o,a,!1,null,null,null),l=u.exports,c=(n("d3b7"),n("8c4f")),f=function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{staticClass:"login"},[r("img",{attrs:{alt:"vue logo",src:n("cf05")}}),r("LoginComponent",{attrs:{msg:"Welcome to Our TimeCard App"}})],1)},p=[],d=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"inputForm"},[n("h1",[e._v(e._s(e.msg))]),n("el-form",{ref:"loginForm",staticClass:"loginForm",attrs:{model:e.loginForm,"status-icon":"",rules:e.rules,"label-width":"120px"}},[n("el-form-item",{attrs:{label:"ログインID",prop:"loginId"}},[n("el-input",{attrs:{type:"loginId",autocomplete:"off"},model:{value:e.loginForm.loginId,callback:function(t){e.$set(e.loginForm,"loginId",t)},expression:"loginForm.loginId"}})],1),n("el-form-item",{attrs:{label:"パスワード",prop:"password",id:"password-div"}},[n("el-input",{attrs:{type:"password",autocomplete:"off"},model:{value:e.loginForm.password,callback:function(t){e.$set(e.loginForm,"password",t)},expression:"loginForm.password"}})],1),n("el-form-item",{attrs:{id:"signUpLinkDiv"}},[n("p",{attrs:{id:"signUpLink"}},[e._v(" Don't have an Account? "),n("router-link",{attrs:{to:"/signup"}},[e._v("SignUp")])],1)]),n("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.submitForm("loginForm")}}},[e._v("ログイン")])],1)],1)},m=[],g=n("5c96"),v=n.n(g),b=(n("0fae"),n("c3ff")),h=n.n(b);r["default"].use(v.a,{locale:h.a});var y={name:"LoginComponent",props:{msg:String},data:function(){return{ok:!1,error:!1,loginForm:{loginId:"",password:""},rules:{loginId:[{required:!0,message:"ログインIDを入力してください"},{max:16,message:"16文字以内で入力してください。"}],password:[{required:!0,message:"パスワードを入力してください。"},{max:30,message:"30(暫定)文字以内で入力してください。"}]}}},methods:{submitForm:function(e){this.$refs[e].validate((function(e){if(!e)return alert("error submit!!"),!1;alert("submit!")}))}}},w=y,_=(n("0eec"),Object(i["a"])(w,d,m,!1,null,"cbf09e8e",null)),x=_.exports,O={name:"logincomponent",components:{LoginComponent:x}},k=O,F=(n("ef27"),Object(i["a"])(k,f,p,!1,null,"1b7dc21e",null)),j=F.exports;r["default"].use(c["a"]);var C=[{path:"/",name:"login",component:j},{path:"/signup",name:"signup",component:function(){return n.e("about").then(n.bind(null,"5c9c"))}}],E=new c["a"]({mode:"history",base:"/",routes:C}),S=E,I=n("2f62");r["default"].use(I["a"]);var L=new I["a"].Store({state:{},mutations:{},actions:{},modules:{}});r["default"].config.productionTip=!1,new r["default"]({router:S,store:L,render:function(e){return e(l)}}).$mount("#app")},6341:function(e,t,n){},"73af":function(e,t,n){},"85ec":function(e,t,n){},cf05:function(e,t,n){e.exports=n.p+"static/img/logo.82b9c7a5.png"},ef27:function(e,t,n){"use strict";var r=n("6341"),o=n.n(r);o.a}});
//# sourceMappingURL=app.e54fe6ac.js.map