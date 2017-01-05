package 

import (
    ".Users/lib/context"
)

type UsersRepo struct {
    UsersList []model.Users
    datasetSize int
}

func NewUsersRepository() *UsersRepo {
    return &UsersRepo {
        UsersList: make([]model.Users, 0),
        datasetSize: 30,
    }
}

    func (r *UsersRepo) UsersSearchGet() {

    }
    func (r *UsersRepo) UsersSelfFeedGet() {

    }
    func (r *UsersRepo) UsersSelfMediaLikedGet() {

    }
    func (r *UsersRepo) UsersUserIdGet() {

    }
    func (r *UsersRepo) UsersUserIdMediaRecentGet() {

    }
