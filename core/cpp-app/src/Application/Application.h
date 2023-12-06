#ifndef APPLICATION_APPLICATION_H
    #define APPLICATION_APPLICATION_H

    #include <iostream>

    #ifdef __linux__
        #include <unistd.h>
        #include <termios.h>
    #else
        #include <conio.h>
    #endif

    class Application
    {
        public:
            Application();

            ~Application();

            bool isExec() const;
            void isExec(const bool is_exec);

            void exec();

        private:
            bool is_exec_;
    };
#endif
