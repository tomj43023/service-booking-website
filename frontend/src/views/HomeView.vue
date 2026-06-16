<template>
  <section class="hero">
    <div class="hero-content">
      <p class="eyebrow">Trusted local cleaning service</p>
      <h1>Fresh, calm spaces without losing your weekend.</h1>
      <p class="hero-copy">
        BrightCare helps busy households and small offices book reliable cleaning in minutes.
        Choose a service, pick a time, and our team handles the rest.
      </p>
      <div class="hero-actions">
        <RouterLink class="button" to="/booking">Book Now</RouterLink>
        <RouterLink class="button button-secondary" to="/services">View Services</RouterLink>
      </div>
      <dl class="trust-strip">
        <div>
          <dt>4.9/5</dt>
          <dd>Average rating</dd>
        </div>
        <div>
          <dt>24h</dt>
          <dd>Fast confirmation</dd>
        </div>
        <div>
          <dt>650+</dt>
          <dd>Homes cleaned</dd>
        </div>
      </dl>
    </div>
    <div class="hero-image">
      <img
        src="https://images.unsplash.com/photo-1581578731548-c64695cc6952?auto=format&fit=crop&w=1000&q=80"
        alt="Cleaning professional preparing a bright home"
      />
      <div class="floating-card">
        <strong>Next available</strong>
        <span>Tomorrow, 9:00 AM</span>
      </div>
    </div>
  </section>

  <section class="section">
    <div class="section-heading">
      <p class="eyebrow">Why clients choose us</p>
      <h2>Clear pricing, careful teams, simple scheduling.</h2>
    </div>
    <div class="feature-grid">
      <article>
        <span class="feature-icon">01</span>
        <h3>Reliable arrivals</h3>
        <p>Appointments stay organized from booking to confirmation, so clients know what happens next.</p>
      </article>
      <article>
        <span class="feature-icon">02</span>
        <h3>Service clarity</h3>
        <p>Each service lists price, duration, and expected scope before the customer commits.</p>
      </article>
      <article>
        <span class="feature-icon">03</span>
        <h3>Admin friendly</h3>
        <p>The internal dashboard keeps Pending, Confirmed, and Cancelled appointments easy to scan.</p>
      </article>
    </div>
  </section>
</template>

