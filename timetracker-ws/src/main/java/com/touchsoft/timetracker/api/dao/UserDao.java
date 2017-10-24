package com.touchsoft.timetracker.api.dao;


import com.touchsoft.timetracker.api.model.User;
import com.touchsoft.timetracker.util.UserUtil;
import com.touchsoft.timetracker.util.hibernate.CustomHibernateDaoSupport;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository(value = "userDao")
public class UserDao extends CustomHibernateDaoSupport {

    @Transactional(readOnly = true)
    public User getUserByLogin(String login) throws UserNotFoundException {
        Query query = getSessionFactory().getCurrentSession().
                createQuery("from User where login=:login and isActive=true ORDER BY name").
                setString("login", login);
        try {
            query.list().get(0);
        } catch (IndexOutOfBoundsException ex) {
            throw new UserNotFoundException();
        }
        return (User) query.list().get(0);

    }

    @Transactional(readOnly = true)
    public List getUserByViewFilter(String viewFilter) {
        List<Long> userId = UserUtil.getUserId(viewFilter);
        List users = new ArrayList(userId.size());
        for (Long id : userId) {
            users.add(getUser(id));
        }
        return users;
    }

    @Transactional(readOnly = true)
    public String getPasswordHash(CharSequence charSequence) {
        Query query = getSessionFactory().getCurrentSession().
                createSQLQuery("SELECT OLD_PASSWORD(:password)").
                setString("password", charSequence.toString());
        return new String((byte[]) query.list().get(0));
    }

    @Transactional(readOnly = true)
    public List getUsers() {
        Query query = getSessionFactory().getCurrentSession().
                createQuery("SELECT ut.user from UserTeam ut " +
                        "WHERE ut.checked=true " +
                        "and ut.team.id=1 " +
                        "and ut.user.isActive=true " +
                        "and ut.user.manager=2 " +
                        "ORDER BY ut.user.name");
        List<User> users = query.list();
        List newUser = new ArrayList<>(users.size());
        for (User user : users) {
            newUser.add(getUser(user.getId()));
        }
        return newUser;
    }

    @Transactional(readOnly = true)
    public Object getUser(String login) {
        Query query = getSessionFactory().getCurrentSession().
                createSQLQuery("SELECT\n" +
                        "  u_id         AS id,\n" +
                        "  u_name       AS name,\n" +
                        "  u_manager_id AS manager,\n" +
                        "  u_comanager  AS coManager,\n" +
                        "  u_login      AS login,\n" +
                        "  u_viewmanager AS viewManager\n" +
                        "FROM users\n" +
                        "WHERE u_login = :login");
        query.setString("login", login);
        List list = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
        ((List<Map>) list).get(0).put("team", getTeamByUser(login));
        return list.get(0);
    }

    @Transactional(readOnly = true)
    public Object getUser(Long id) {
        Query query = getSessionFactory().getCurrentSession().
                createSQLQuery("SELECT\n" +
                        "  u_id         AS id,\n" +
                        "  u_name       AS name,\n" +
                        "  u_manager_id AS manager,\n" +
                        "  u_comanager  AS coManager,\n" +
                        "  u_login      AS login,\n" +
                        "  u_viewmanager AS viewManager\n" +
                        "FROM users\n" +
                        "WHERE u_id = :id");
        query.setLong("id", id);
        List list = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
        ((List<Map>) list).get(0).put("team", getTeamByUser(id));
        return list.get(0);
    }

    @Transactional(readOnly = true)
    private List getTeamByUser(String login) {
        Query query = getSessionFactory().getCurrentSession().
                createSQLQuery("SELECT DISTINCT \n" +
                        "  ub_id_p      AS teamId,\n" +
                        "  p_name       AS teamName\n" +
                        "FROM users\n" +
                        "  LEFT JOIN user_bind ON ub_id_u = u_id\n" +
                        "  LEFT JOIN projects ON p_id = ub_id_p\n" +
                        "WHERE u_login = :login AND ub_checked = 1");
        query.setString("login", login);
        return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    }

    @Transactional(readOnly = true)
    private List getTeamByUser(Long id) {
        Query query = getSessionFactory().getCurrentSession().
                createSQLQuery("SELECT DISTINCT \n" +
                        "  ub_id_p      AS teamId,\n" +
                        "  p_name       AS teamName\n" +
                        "FROM users\n" +
                        "  LEFT JOIN user_bind ON ub_id_u = u_id\n" +
                        "  LEFT JOIN projects ON p_id = ub_id_p\n" +
                        "WHERE u_id = :id AND ub_checked = 1");
        query.setLong("id", id);
        return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    }

    @Transactional(readOnly = true)
    public String getViewFilter(String login) {
        Query query = getSessionFactory().getCurrentSession().
                createSQLQuery("SELECT\n" +
                        "  u_viewfilter AS viewFilter\n" +
                        "FROM users\n" +
                        "WHERE u_login = :login");
        query.setString("login", login);
        return (String) query.list().get(0);
    }

}
