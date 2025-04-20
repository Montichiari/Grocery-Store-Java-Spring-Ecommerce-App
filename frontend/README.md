# GDiSA Java EE CA Frontend (by Team 3)

The entire frontend has been created by Jared Chua, built with Vite in React. The application has been coded in Typescript for more typesafety, and primarily powered by [Tanstack Query](https://tanstack.com/query/latest) for API state management, caching, and data fetching.

## Project Structure

```
src
|-- /app <-- Webpage entry point
|-- /assets <-- Static assets used throughout the app
|-- /components <-- Individual components used in /layouts
|-- /layouts <-- Views that corresponds to each core feature
|-- /routes <-- List of navigatable routes
|-- /stores <-- Zustand stores for global state management
|-- /type <-- List of declared types and interfaces
|-- /utils <-- Misc. helper functions
```

## Deployment

To deploy just the React app (the app won't work without the backend and db running)

```bash
  npm install
  npm run build
  npm run preview
```

## Methodology

- Each component that requires extensive use of React's hooks will have its own refactored custom hook that only exposes the methods and variables that the component uses
- An API wrapper has been created in the `/utils` folder to fix the HTTP header settings to communicate with the backend API
- The API wrapper also requires the `body` schema (for methods that require it) and the API response to be typed for verbosity.
- Notifications (or rather, toasts) is primarily used to provide feedback when a successful mutation has occurred (e.g. creating a new customer account)
