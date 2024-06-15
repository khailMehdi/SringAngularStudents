import {
  ActivatedRouteSnapshot,
  CanActivate,
  CanActivateFn,
  GuardResult,
  MaybeAsync, Router,
  RouterStateSnapshot
} from '@angular/router';
import {Injectable} from "@angular/core";
import {AuthService} from "../services/auth.service";
@Injectable()

export class AuthGuard {

  constructor(private authService : AuthService, private router : Router) {
  }
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): MaybeAsync<GuardResult> {
    if( this.authService.isAuthenticated){
      let requiredRoles=route.data['roloes'];
      let userRoles :string[]=this.authService.roles;

      // for(let role: string of userRoles){
      //   if(requiredRoles.includes(role)){
      //     return true;
      //   }
      // }
      return  true;
    } else {
      this.router.navigateByUrl("/login")
      return false;
    }
  }
}

export class AuthorizationGuard {
}
