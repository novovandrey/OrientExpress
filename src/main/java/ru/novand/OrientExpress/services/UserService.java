package ru.novand.OrientExpress.services;

//@Service
//public class UserService implements IUserService {
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Transactional
//    @Override
//    public User registerNewUserAccount(UserDto accountDto)
//            throws EmailExistsException {
//
//        if (emailExist(accountDto.getEmail())) {
//            throw new EmailExistsException(
//                    "There is an account with that email address:  + accountDto.getEmail());
//        }
//        User user = new User();
//        user.setFirstName(accountDto.getFirstName());
//        user.setLastName(accountDto.getLastName());
//        user.setPassword(accountDto.getPassword());
//        user.setEmail(accountDto.getEmail());
//        user.setRoles(Arrays.asList("ROLE_USER"));
//        return repository.save(user);
//    }
//    private boolean emailExist(String email) {
//        User user = repository.findByEmail(email);
//        if (user != null) {
//            return true;
//        }
//        return false;
//    }
//}