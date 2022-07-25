package hr.algebra.repository;

import hr.algebra.dao.SqlRepo;


public class RepoFactory {

    private RepoFactory() {}

    public static ISqlRepo getRepository() throws Exception {
        return new SqlRepo();
    }
}
