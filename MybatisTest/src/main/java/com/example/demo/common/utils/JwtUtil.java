package com.example.demo.common.utils;


import com.example.demo.entity.User;
import com.example.demo.model.TokenVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    private static final long EXPIRE_TIME = 3 * 60 * 1000;
    private static final String SECRET = "hello world";
    private static final String tokenHeader = "myToken";


    //生成Token
    public static String generateToken(String uname){
        Map<String, Object> claims = new HashMap<>();
//        claims.put("uid", user.getUid());
        claims.put("username", uname);
//        claims.put("tag", user.getTag());
        claims.put("created", new Date());

        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, SECRET).compact();

    }

    public static TokenVO getUserFromToken(HttpServletRequest request){
        String token = request.getHeader(tokenHeader);
//        System.out.println(token);//打印token
        if (token != null) {
            Claims claims = getClaimsFromToken(token);
            if (claims == null) {
                System.out.println("claims == null");
                return null;
            }
            String username = claims.get("username").toString();
            if (username == null) {
                System.out.println("username == null");
                return null;
            }
            if (isTokenExpired(token)) {
                System.out.println("过期");
                return null;
            }
            //if (validateToken(token, username)) {

            return new TokenVO(username);
            //}
        }
        return null;
    }

    //从Cookies中获取用户信息
    public static TokenVO getUserFromCookie(HttpServletRequest request){
        String token = null;
        if (request.getCookies() == null) {
            return null;
        }
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals(tokenHeader)) {
                token = cookie.getValue();
                break;
            }
        }
        if (token != null) {
            Claims claims = getClaimsFromToken(token);
            if (claims == null) {
                return null;
            }
            String username = claims.get("username").toString();
            if (username == null) {
                return null;
            }
            if (isTokenExpired(token)) {
                return null;
            }
            return new TokenVO(username);
        }
        return null;
    }

//    private static Boolean validateToken(String token, String username){
//        String userName = getUserFromToken
//    }


    //令牌中获取数据
    public static Claims getClaimsFromToken(String token){
        Claims claims;
        try{
            claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        }catch(Exception e){
            System.out.println(e);
            claims = null;
        }
        return claims;
    }


    //检查令牌是否过期
    public static Boolean isTokenExpired(String token){
        try{
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
//            System.out.println("时间" + expiration.before(new Date()));
            return expiration.before(new Date());
        }catch (Exception e){
            return true;
        }
    }




//    public static String getUserName(String token){
//        Claims claims = getClaimFromToken(token);
//        return claims.get("username").toString();
//    }
}
