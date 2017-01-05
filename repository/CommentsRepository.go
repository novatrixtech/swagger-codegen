package 

import (
    ".Comments/lib/context"
)

type CommentsRepo struct {
    CommentsList []model.Comments
    datasetSize int
}

func NewCommentsRepository() *CommentsRepo {
    return &CommentsRepo {
        CommentsList: make([]model.Comments, 0),
        datasetSize: 30,
    }
}

    func (r *CommentsRepo) MediaMediaIdCommentsDelete() {

    }
    func (r *CommentsRepo) MediaMediaIdCommentsGet() {

    }
    func (r *CommentsRepo) MediaMediaIdCommentsPost() {

    }
