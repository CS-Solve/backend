package com.server.computerscience;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.server.computerscience.login.aspect.LoginAspect;

@Import(LoginAspect.class)
@EnableAspectJAutoProxy
public class ViewControllerTest extends ControllerTest {
}
