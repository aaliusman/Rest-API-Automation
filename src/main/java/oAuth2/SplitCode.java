package oAuth2;

public class SplitCode {
    public static void main(String[] args) {

        String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AY0e-g7IdRBIco158ER5tD6g2XC8H3Ti5TG1fl-QZ-4DbdiKXo9RsqIQfdHTEKT5eUNeRQ&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none#";
        //Need only this part 4%2F0AY0e-g7IdRBIco158ER5tD6g2XC8H3Ti5TG1fl-QZ-4DbdiKXo9RsqIQfdHTEKT5eUNeRQ
        String partialCode = url.split("code=")[1];
        String code = partialCode.split("&scope")[0];
        System.out.println(code);

    }
}
