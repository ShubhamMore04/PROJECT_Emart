import React from 'react';
import { useEffect } from 'react';

const HomePage = () => {
  useEffect(() => {
    // Disable scrolling on the body
    document.body.style.overflow = 'hidden';

    // Enable scrolling on cleanup
    return () => {
      document.body.style.overflow = 'unset';
    };
  }, []);
  return (
    <div style={{ width: '100%', height: '100vh' }}>
      <div className="video-content">
        <video className="d-block w-100" autoPlay loop muted controls>
          <source src="/Images/web2.mp4" type="video/mp4" />
          Your browser does not support the video tag.
        </video>
        
      </div>
    </div>
  );
};

export default HomePage;