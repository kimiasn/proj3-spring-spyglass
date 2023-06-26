package com.skillstorm.demo.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

@Controller
//            allows cookies to send,
@CrossOrigin(allowCredentials = "true", originPatterns = "http://localhost:5173")
public class UserController {

	@Autowired
	private OAuth2AuthorizedClientService clientService;

	// Redirects the user to the frontend application (S3 bucket, localhost:5173)
	// Users should ONLY access the app using this
	// This is done to get the JSESSIONID cookie established (login with Google)
	@GetMapping("/signin")
	public RedirectView redirectView() {
		System.out.println("sign in redirect");
		RedirectView redirectView = new RedirectView("http://localhost:5173");
		return redirectView;
	}

	@GetMapping("/userinfo")
	@ResponseBody // Send the data as JSON
	public Map<String, Object> userInfo(@AuthenticationPrincipal OAuth2User oAuthuser) {

		// Info about the user
		Map<String, Object> user = oAuthuser.getAttributes();
		System.out.println(user.get("sub"));
		return user;
	}

	// Return access token
	@GetMapping("/access")
	@ResponseBody
	public String accessToken(Authentication auth) {
		if (auth instanceof OAuth2AuthenticationToken) {
			OAuth2AuthenticationToken authToken = (OAuth2AuthenticationToken) auth;
			OAuth2AuthorizedClient client = clientService
					.loadAuthorizedClient(authToken.getAuthorizedClientRegistrationId(), authToken.getName());
			return client.getAccessToken().getTokenValue();
		}
		return "";
	}

	// Return id token
	@GetMapping("/id")
	@ResponseBody
	public String idToken(@AuthenticationPrincipal OAuth2User user) {
		return ((DefaultOidcUser) user).getIdToken().getTokenValue();
	}
}