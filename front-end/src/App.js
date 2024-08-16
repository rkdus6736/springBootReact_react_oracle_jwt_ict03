import logo from './logo.svg';
import './App.css';
import Header from './components/Header';
import AppContent from './components/AppContent';

function App() {
  return (
    <div className="App">
      <Header pageTitle="Front authenticated with JWT" logoSrc={logo} />
        <div className="container-fluid">
          <div className="row">
            <div className="col">
              <AppContent />
            </div>
          </div>
        </div>
    </div>
  );
}

export default App;
