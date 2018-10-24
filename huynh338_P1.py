#   CSCI 1913
#   Donald Huynh
class Random():
    def __init__(self, seed):
        self.__seed = seed

    def next(self):
        return (16907 * self.__seed) % (2147483648 - 1)

    def choose(self, max):
        return self.next() % max

