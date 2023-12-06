#include "Application.h"

#ifdef __linux__
    int getch()
    {
        struct termios oldattr, newattr;

        tcgetattr(STDIN_FILENO, &oldattr);

        newattr = oldattr;
        newattr.c_lflag &= ~(ICANON | ECHO);
        tcsetattr(STDIN_FILENO, TCSANOW, &newattr);
        int ch = getchar();

        tcsetattr(STDIN_FILENO, TCSANOW, &oldattr);

        return ch;
    }
#endif

Application::Application(): is_exec_(false)
{
    this->isExec(true);
}

Application::~Application()
{
    this->isExec(false);
}

bool Application::isExec() const
{
    return this->is_exec_;
}

void Application::isExec(const bool is_exec)
{
    this->is_exec_ = is_exec;
}

void Application::exec()
{
    while (this->isExec()) {
        int ch = getch();

        if (ch == 'q') {
            this->isExec(false);
        } else {
            std::cout << "character: " << ch << std::endl;
        }
    }
}
