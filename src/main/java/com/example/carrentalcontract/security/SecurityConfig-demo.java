// @Configuration
// @EnableWebSecurity
// @EnableGlobalMethodSecurity(prePostEnabled = true)
// public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//         http
//             .authorizeRequests()
//                 .anyRequest().authenticated()
//                 .and()
//             .formLogin()
//                 .loginPage("/login/auth").permitAll()
//                 .loginProcessingUrl("/login")
//                 .usernameParameter("username")
//                 .passwordParameter("password")
//                 .successHandler(new AuthenticationSuccessHandler() {
//                     @Override
//                     public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
//                         httpServletResponse.getWriter().write("success");
//                         httpServletResponse.getWriter().close();
//                     }
//                 })
//                 .failureHandler(new AuthenticationFailureHandler() {
//                     @Override
//                     public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
//                         httpServletResponse.getWriter().write("fail");
//                         httpServletResponse.getWriter().close();
//                     }
//                 })
//                 .and()
//             .logout()
//                 .logoutUrl("/logout")
//                 .logoutSuccessUrl("/login/logout").permitAll()
//                 .and()
//             .csrf().disable();
//     }
//
//
//
//     public void configure(AuthenticationManagerBuilder auth) throws Exception{
//         auth.userDetailsService(userDetailsService()).passwordEncoder(NoOpPasswordEncoder.getInstance());
//     }
//
//     @Bean
//     public UserDetailsService userDetailsService(){
//         return new UserDetailsService() {
//             @Autowired
//             UserMapper userMapper;
//             @Override
//             public UserDetails loadUserByUsername(String code) throws UsernameNotFoundException {
//                 User user = userMapper.queryUserAndAuthoritiesByCode(code);
//                 if (user==null){
//                     return new org.springframework.security.core.userdetails.User(null,null,false,false,false,false,null);
//                 }
//                 return new org.springframework.security.core.userdetails.User(code,user.getPassword(),true,true,true,true,translatePowerToGrantedAuthority(user.getPowers()));
//             }
//         };
//     }
//
//     private static List<GrantedAuthority> translatePowerToGrantedAuthority(List<Power> powers){
//         List<GrantedAuthority> arrayList = new ArrayList();
//         if (powers==null){
//             return null;
//         }
//         powers.forEach(item->{
//             if(item.isRole()){
//                 arrayList.add(new SimpleGrantedAuthority("ROLE_"+item.getName()));
//             }else {
//                 arrayList.add(new SimpleGrantedAuthority("AUTH_"+item.getName()));
//             }
//
//         });
//         return arrayList;
//     }
// }
// ;*/
