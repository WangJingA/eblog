<#include "../import/top.ftl">
<div class="panel">
   <div class="panel-body">
      <form class="form-horizontal" action="/admin/userList" method="post">
         <div class="form-group">
            <label for="userName" class="col-sm-1">用户名</label>
            <div class="col-sm-2">
               <input type="text" class="form-control" id="userName" placeholder="用户名">
            </div>
            <div class="col-sm-1">
               <button type="submit" class="btn-primary btn-large">查询</button>
            </div>
         </div>
      </form>
   </div>
</div>
   <#if userInfoList??>
   <div class="panel">
      <div class="panel-body">
         <table class="table">
            <thead>
            <tr>
               <th>注册时间</th>
               <th>用户名</th>
               <th>是否冻结</th>
               <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <#list userInfoList! as userInfo>
               <tr>
                  <td>${userInfo.sysUserRegisterTime?string('yyyy-MM-dd HH:mm:ss')}</td>
                  <td>${(userInfo.sysUserName)}</td>
                  <td>
                     <#if (userInfo.sysUserFrozen)?? && (userInfo.sysUserFrozen) == 1>
                         <span class="label label-danger">
                            冻结
                         </span>
                        <#else >
                        <span class="label label-success">
                           正常
                        </span>
                     </#if>
                  </td>
                  <td>
                     <button type="button" class="btn-mini"><i class="icon icon-cog"></i>修改</button>
                     <button type="button" class="btn-mini"><i class="icon icon-times"></i>删除</button>
                     <button type="button" class="btn-mini"><i class="icon icon-circle-blank"></i>冻结</button>
                  </td>
               </tr>
            </#list>
            </tbody>
            <tfoot>
            <tr>
               <td>...</td>
               <td>...</td>
            </tr>
            </tfoot>
         </table>
      </div>
   </div>
   <#else >
      <div style="text-align: center;">
         <h3><i class="icon-coffee"></i></h3>
         <h3>暂无数据</h3>
      </div>
   </#if>
<#include "../import/bottom.ftl">
